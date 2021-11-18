package com.example.monitorwise.screen.courses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityCoursesBinding;
import com.example.monitorwise.model.domain.course.Course;
import com.example.monitorwise.screen.disciplines.DisciplinesActivity;
import com.example.monitorwise.screen.user.register.RegisterActivity;
import com.example.monitorwise.util.Constants;

import java.util.ArrayList;
import java.util.List;


public class CoursesActivity extends BaseActivity implements CoursesContract.View {

    private ActivityCoursesBinding mBinding;
    //private CoursesContract.ViewModel viewModel;
    //private CoursesContract.Repository repository;
    private String courseData;


    List<Course> courseName = new ArrayList<>();

    CoursesAdapter adapter = new CoursesAdapter(courseName);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_courses);
        mBinding.rvListCourse.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adapter = new CoursesAdapter(populateItems());
        mBinding.rvListCourse.setAdapter(adapter);

        adapter.setOnClickListener(this::showAlertDialog);
    }

    private void showAlertDialog(String courseName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Você escolheu o curso: " + courseName + ".")
                .setPositiveButton(R.string.confirm, (dialog, id) -> {
                    saveCourseData(courseName);
                })
                .setNegativeButton(R.string.cancelar, (dialog, id) -> {
                });
        builder.create().show();
    }

    private void saveCourseData(String courseName) {
        SharedPreferences.Editor editor = CoursesActivity.this.getSharedPreferences(Constants.REGISTER_SHARED_NAME, Context.MODE_PRIVATE).edit();

        editor.putString(Constants.REGISTER_COURSE_KEY, courseName).apply();
        goToRegister();
    }

    private void goToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private List<Course> populateItems() {
        List<Course> fatecCourses = new ArrayList<>();

        fatecCourses.add(new Course("Análise e Desenvolvimento de Sistemas"));
        fatecCourses.add(new Course("Comércio Exterior"));
        fatecCourses.add(new Course("Gestão de Serviços"));
        fatecCourses.add(new Course("Gestão Empresarial"));
        fatecCourses.add(new Course("Logística Aeroportuária"));
        fatecCourses.add(new Course("Redes de Computadores"));

        return fatecCourses;
    }

}