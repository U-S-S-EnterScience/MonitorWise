package com.example.monitorwise.screen.courses;

import com.example.monitorwise.base.BaseContract;
import com.example.monitorwise.base.BaseView;
import com.example.monitorwise.base.BaseViewModel;
import com.example.monitorwise.model.domain.course.Course;

import java.util.List;

public interface CoursesContract {

    interface View extends BaseView {

    }

    interface ViewModel extends BaseContract.ViewModel {
        List<Course> populateItems();

    }

    interface Repository {
        List<Course> fetchCourses();
        List<Course> populateItems();
    }
}
