package com.example.monitorwise.screen.user.register;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityRegisterBinding;
import com.example.monitorwise.databinding.ContentUserRegisterBinding;
import com.example.monitorwise.model.domain.account.Account;
import com.example.monitorwise.screen.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    private ActivityRegisterBinding mBinding;
    private ContentUserRegisterBinding mUserRegisterBinding;
    private com.example.monitorwise.databinding.ContentUserRegisterBinding mUserRegisterFieldsBinding;
    private FirebaseAuth mAuth;
    private String turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mUserRegisterFieldsBinding = mBinding.includeContentUserRegister;
        mUserRegisterFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
        mUserRegisterFieldsBinding.setListener(this);
        mUserRegisterFieldsBinding.includeContentCourseTurn.setListener(this);


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        turn = "";
        switch(view.getId()) {
            case R.id.radio_button_morning:
                if (checked)
                    turn = "Diurno";
                    break;
            case R.id.radio_button_night:
                if (checked)
                    turn = "Noturno";
                    break;
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_register) {
            validateKeys();
        }
    }

    public void registerUser() {
        Account account = new Account(
                getEmail(),
                getValidateKey(),
                turn,
                getCourse(),
                getClassName()
        );
                mUserRegisterFieldsBinding.btnRegister.setVisibility(View.INVISIBLE);
                mUserRegisterFieldsBinding.progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(getEmail(), getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    account.setId(mAuth.getUid());
                                    account.save();
                                    startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                                    finish();
                                } else {
                                    String error = Objects.requireNonNull(task.getException()).getMessage();
                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "" + error,
                                            Toast.LENGTH_SHORT).show();
                                }
                                mUserRegisterFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
                                mUserRegisterFieldsBinding.btnRegister.setVisibility(View.VISIBLE);
                            }
                        });

    }

    public void validateKeys() {
        if(validateClick()) {
            if(getPassword().equals(getPasswordAgain())) {
                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("validateKeys").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            if(Objects.equals(postSnapshot.getValue(String.class), getValidateKey())) {
                                registerUser();
                                return;
                            }
                        }
                        Toast.makeText(
                                RegisterActivity.this,
                                "Chave de ativação informada não conforme, favor inserir uma chave valida.",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(
                                RegisterActivity.this,
                                "" + error.toException(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(
                        RegisterActivity.this,
                        "Senhas diferentes, favor inserir senha iguais.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(
                    RegisterActivity.this,
                    "Campos obrigatórios não preenchidos, favor inseri-los.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validateClick() {
        return !getCourse().equals("Escolha seu curso")
                && !getClassName().equals("Disciplinas que deseja ministrar")
                && !TextUtils.isEmpty(getEmail())
                && !TextUtils.isEmpty(getPassword())
                && !TextUtils.isEmpty(getPasswordAgain())
                && !TextUtils.isEmpty(getValidateKey());
    }

    @Override
    public String getCourse() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.includeContentCourseChoose.textViewChooseCourse.getText().toString() : "";
    }

    @Override
    public String getClassName() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.includeContentDisciplineChoose.textViewChooseCourse.getText().toString() : "";
    }

    @Override
    public String getValidateKey() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtActiveKeyRegister.getText().toString() : "";
    }

    @Override
    public String getEmail() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtLoginRegister.getText().toString() : "";
    }

    @Override
    public String getPassword() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtPasswordRegister.getText().toString() : "";
    }

    @Override
    public String getPasswordAgain() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtPasswordConfirm.getText().toString() : "";
    }
}