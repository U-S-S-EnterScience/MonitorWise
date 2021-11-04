package com.example.monitorwise.screen.user.register;

import com.example.monitorwise.base.BaseContract;
import com.example.monitorwise.base.BaseView;

public interface RegisterContract {

    interface View extends BaseView {
        String getEmail();

        String getPassword();

        String getPasswordAgain();

        String getValidateKey();

        String getClassName();

        String getCourse();
    }

    interface ViewModel extends BaseContract.ViewModel {

    }

    interface Repository {

    }
}
