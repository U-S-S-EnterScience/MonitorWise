package com.example.monitorwise.screen.user.login;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityLoginBinding;
import com.example.monitorwise.screen.home.HomeActivity;
import com.example.monitorwise.screen.user.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        com.example.monitorwise.databinding.ContentUserLoginBinding mUserLoginFieldsBinding = mBinding.includeContentLogin;
        mUserLoginFieldsBinding.setListener(this);

        mUserLoginFieldsBinding.progressBar.setVisibility(android.view.View.INVISIBLE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, HomeActivity.class));
                finish();
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