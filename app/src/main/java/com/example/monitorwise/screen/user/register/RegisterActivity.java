package com.example.monitorwise.screen.user.register;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityRegisterBinding;
import com.example.monitorwise.databinding.ContentUserRegisterBinding;
import com.example.monitorwise.model.domain.account.Account;
import com.example.monitorwise.screen.courses.CoursesActivity;
import com.example.monitorwise.screen.disciplines.DisciplinesActivity;
import com.example.monitorwise.screen.home.HomeActivity;
import com.example.monitorwise.util.Constants;
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
    private RegisterContract.ViewModel viewModel;
    private RegisterContract.Repository repository;

    private FirebaseAuth mAuth;
    private String turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }


    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mUserRegisterBinding = mBinding.includeContentUserRegister;
        mAuth = FirebaseAuth.getInstance();
        mUserRegisterBinding.progressBar.setVisibility(View.INVISIBLE);
        mUserRegisterBinding.setListener(this);
        mUserRegisterBinding.includeContentCourseChoose.setListener(this);
        mUserRegisterBinding.includeContentCourseTurn.setListener(this);
        mUserRegisterBinding.includeContentDisciplineChoose.setListener(this);

    }

    private void updateDisciplinesData() {
        mBinding.includeContentUserRegister.includeContentDisciplineChoose.textViewChooseCourse.setText(getIntent().getSerializableExtra(Constants.DISCIPLINE_KEY).toString());
    }

    private void updateCourseData() {
        mBinding.includeContentUserRegister.includeContentCourseChoose.textViewChooseCourse.setText(getIntent().getSerializableExtra(Constants.COURSE_KEY).toString());
        mBinding.includeContentUserRegister.includeContentCourseChoose.textViewChooseCourse.setTextColor(getResources().getColor(R.color.black));
        mBinding.includeContentUserRegister.includeContentCourseChoose.viewPin.setBackgroundColor(getResources().getColor(R.color.black));
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        turn = "";
        switch (view.getId()) {
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

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if (view.getId() == R.id.check_box_show_password_register)
            if (checked)
                showPassword();
            else
                hidePassword();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void initializeComponents(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.COURSE_KEY))
            updateCourseData();
        if (intent.hasExtra(Constants.DISCIPLINE_KEY))
            updateDisciplinesData();
        return;
    }

    private void showPassword() {
        mUserRegisterBinding.checkBoxShowPasswordRegister.setChecked(true);
        mUserRegisterBinding.edtPasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        mUserRegisterBinding.edtPasswordConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    private void hidePassword() {
        mUserRegisterBinding.checkBoxShowPasswordRegister.setChecked(false);
        mUserRegisterBinding.edtPasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mUserRegisterBinding.edtPasswordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_register:
                validateKeys();
                break;

            case R.id.include_content_course_choose:
                goToCoursesActivity();
                break;

            case R.id.include_content_discipline_choose:
                goToDisciplinesActivity();
                break;
        }
    }


    private void goToCoursesActivity() {
        startActivity(new Intent(this, CoursesActivity.class));
        onStop();
    }


    private void goToDisciplinesActivity() {
        startActivity(new Intent(this, DisciplinesActivity.class));
        onStop();
    }


    public void registerUser() {
        Account account = new Account(
                getEmail(),
                getValidateKey(),
                turn,
                getCourse(),
                getClassName()
        );
        mUserRegisterBinding.btnRegister.setVisibility(View.INVISIBLE);
        mUserRegisterBinding.progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(getEmail(), getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        account.setId(mAuth.getUid());
                        account.save();
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        String error = Objects.requireNonNull(task.getException()).getMessage();
                        Toast.makeText(
                                RegisterActivity.this,
                                "" + error,
                                Toast.LENGTH_SHORT).show();
                    }
                    mUserRegisterBinding.progressBar.setVisibility(View.INVISIBLE);
                    mUserRegisterBinding.btnRegister.setVisibility(View.VISIBLE);
                });

    }

    public void validateKeys() {
        if (validateClick()) {
            if (getPassword().equals(getPasswordAgain())) {
                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("validateKeys").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            if (Objects.equals(postSnapshot.getValue(String.class), getValidateKey())) {
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