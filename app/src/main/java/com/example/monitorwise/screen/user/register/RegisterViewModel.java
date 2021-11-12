package com.example.monitorwise.screen.user.register;

import com.example.monitorwise.base.BaseViewModel;
import com.example.monitorwise.model.domain.course.Course;

import java.util.ArrayList;

public class RegisterViewModel extends BaseViewModel<RegisterContract.View>
        implements RegisterContract.ViewModel {

    private RegisterContract.Repository repository;


    @Override
    public ArrayList<Course> fetchCourse() {
        return repository.fetchCourse();
    }
}
