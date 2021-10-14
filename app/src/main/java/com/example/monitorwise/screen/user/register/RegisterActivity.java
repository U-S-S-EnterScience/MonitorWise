package com.example.monitorwise.screen.user.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityRegisterBinding;
import com.example.monitorwise.databinding.ContentUserRegisterBinding;
import com.example.monitorwise.model.domain.account.Account;
import com.example.monitorwise.screen.home.HomeActivity;
import com.example.monitorwise.screen.user.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    private ActivityRegisterBinding mBinding;
    private ContentUserRegisterBinding mUserRegisterBinding;
    private com.example.monitorwise.databinding.ContentUserRegisterBinding mUserRegisterFieldsBinding;
    private FirebaseAuth mAuth;
    private List<String> validateKeyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        validateKeyList = Arrays.asList("MAT202102D", "MAT202102N", "ALG202102D", "ALG202102N", "INF202102D", "INF202102N", "ING202102D", "ING202102N", "SIM202102D");
        mUserRegisterFieldsBinding = mBinding.includeContentUserRegister;

        mUserRegisterFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
        mUserRegisterFieldsBinding.setListener(this);
    }

    @Override
    public void onClick(View view) {
        Account account = new Account(
                mAuth.getUid(),
                getEmail(),
                getValidateKey(),
                getPeriod(),
                getCourse()
        );

        if (view.getId() == R.id.btn_register) {
            //Colocar o parâmetro disabled no botão para só liberar o click quando tdos os campos estiverem preenchidos
            if(!TextUtils.isEmpty(getEmail()) || !TextUtils.isEmpty(getPassword()) || !TextUtils.isEmpty(getPasswordAgain())) {
                if(validateKeyList.contains(getValidateKey())) {
                    if(getPassword().equals(getPasswordAgain())) {
                        mUserRegisterFieldsBinding.btnRegister.setVisibility(View.INVISIBLE);
                        mUserRegisterFieldsBinding.progressBar.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(getEmail(), getPassword())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            account.save();
                                            startActivity(new Intent(
                                                    RegisterActivity.this,
                                                    HomeActivity.class)
                                            );
                                            finish();
                                        } else {
                                            String error = task.getException().getMessage();
                                            Toast.makeText(
                                                    RegisterActivity.this,
                                                    "" + error,
                                                    Toast.LENGTH_SHORT).show();
                                            mUserRegisterFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
                                            mUserRegisterFieldsBinding.btnRegister.setVisibility(View.VISIBLE);
                                        }
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
                            "Chave de ativação informada não conforme, favor inserir uma chave valida.",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(
                        RegisterActivity.this,
                        "Campos obrigatórios não preenchidos, favor inseri-los.",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    public String getCourse() {
        return "Análise e Desenvolvimento de Sistemas";
        /*return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtCourse.getText().toString() : "";*/
    }
    public String getValidateKey() {
        return "MAT202102D";
        /*return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtValidateKey.getText().toString() : "";*/
    }
    @Override
    public String getEmail() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtLoginRegister.getText().toString() : "";
    }

    @Override
    public String getPeriod() {
        return "Noturno";
        //return null;
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