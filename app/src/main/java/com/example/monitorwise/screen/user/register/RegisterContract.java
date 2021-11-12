package com.example.monitorwise.screen.user.register;

import com.example.monitorwise.base.BaseContract;
import com.example.monitorwise.base.BaseView;
import com.example.monitorwise.model.domain.course.Course;

import java.util.ArrayList;

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
        ArrayList<Course> fetchCourse();
    }

    interface Repository {
        ArrayList<Course> fetchCourse();

    }
}
