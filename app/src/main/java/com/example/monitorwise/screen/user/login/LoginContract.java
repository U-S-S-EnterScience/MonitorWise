package com.example.monitorwise.screen.user.login;

import com.example.monitorwise.base.BaseContract;
import com.example.monitorwise.base.BaseView;

public interface LoginContract {

    interface View extends BaseView {
        String getEmail();
        String getPassword();
    }

    interface ViewModel extends BaseContract.ViewModel {

    }

    interface Repository {

    }

}
