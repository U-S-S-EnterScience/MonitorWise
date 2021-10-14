package com.example.monitorwise.screen.user.login;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityLoginBinding;
import com.example.monitorwise.screen.home.HomeActivity;
import com.example.monitorwise.screen.user.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private ActivityLoginBinding mBinding;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private com.example.monitorwise.databinding.ContentUserLoginBinding mUserLoginFieldsBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        onInitView();
    }

    private void onInitView() {
        mAuth = FirebaseAuth.getInstance();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        mUserLoginFieldsBinding = mBinding.includeContentLogin;
        mUserLoginFieldsBinding.setListener(this);

        mUserLoginFieldsBinding.progressBar.setVisibility(android.view.View.INVISIBLE);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if(!TextUtils.isEmpty(getEmail()) || !TextUtils.isEmpty(getPassword())) {
                    mUserLoginFieldsBinding.btnLogin.setVisibility(View.INVISIBLE);
                    mUserLoginFieldsBinding.progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(getEmail(), getPassword())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        startActivity(new Intent(
                                                LoginActivity.this,
                                                HomeActivity.class)
                                        );
                                        finish();
                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(
                                                LoginActivity.this,
                                                "" + error,
                                                Toast.LENGTH_SHORT).show();
                                        mUserLoginFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
                                        mUserLoginFieldsBinding.btnLogin.setVisibility(View.VISIBLE);
                                    }
                                }
                            });

                } else {
                    Toast.makeText(
                            LoginActivity.this,
                            "Insira e-mail e/ou senha",
                            Toast.LENGTH_SHORT).show();
                    mUserLoginFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
                    mUserLoginFieldsBinding.btnLogin.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.btn_sign_up:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.btn_change_password:
                break;
        }
    }

    @Override
    public String getEmail() {
        return mBinding.includeContentLogin != null ?
                mBinding.includeContentLogin.editTextLogin.getText().toString() : "";
    }

    @Override
    public String getPassword() {
        return mBinding.includeContentLogin != null ?
                mBinding.includeContentLogin.editTextPassword.getText().toString() : "";
    }
}