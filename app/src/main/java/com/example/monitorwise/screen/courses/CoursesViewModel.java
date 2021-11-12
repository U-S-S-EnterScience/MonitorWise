package com.example.monitorwise.screen.courses;

import com.example.monitorwise.base.BaseViewModel;
import com.example.monitorwise.model.domain.course.Course;

import java.util.List;

public class CoursesViewModel extends BaseViewModel<CoursesContract.View>
        implements CoursesContract.ViewModel {

    private CoursesContract.Repository repository;

    @Override
    public List<Course> populateItems() {
        return repository.populateItems();

    }
}
