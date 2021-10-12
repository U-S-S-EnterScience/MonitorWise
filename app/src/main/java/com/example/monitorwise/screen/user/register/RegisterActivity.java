package com.example.monitorwise.screen.user.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityRegisterBinding;
import com.example.monitorwise.databinding.ContentUserRegisterBinding;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    private ActivityRegisterBinding mBinding;
    private ContentUserRegisterBinding mUserRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        com.example.monitorwise.databinding.ContentUserRegisterBinding mUserRegisterFieldsBinding = mBinding.includeContentUserRegister;
        mUserRegisterFieldsBinding.progressBar.setVisibility(View.INVISIBLE);
        mUserRegisterFieldsBinding.setListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_register) {

        }
    }

    @Override
    public String getEmail() {
        return mBinding.includeContentUserRegister != null ?
                mBinding.includeContentUserRegister.edtLoginRegister.getText().toString() : "";
    }

    @Override
    public String getPeriod() {
        return null;
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