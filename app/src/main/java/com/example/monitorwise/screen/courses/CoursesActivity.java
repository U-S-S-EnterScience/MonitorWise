package com.example.monitorwise.screen.courses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityCoursesBinding;
import com.example.monitorwise.model.domain.course.Course;
import com.example.monitorwise.screen.user.register.RegisterActivity;
import com.example.monitorwise.util.Constants;

import java.util.ArrayList;
import java.util.List;


public class CoursesActivity extends BaseActivity implements CoursesContract.View {

    private ActivityCoursesBinding mBinding;
    private CoursesContract.ViewModel viewModel;
    private CoursesContract.Repository repository;

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

        adapter.setOnClickListener(new CoursesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String courseName) {
                Intent intent = new Intent(CoursesActivity.this, RegisterActivity.class);
                intent.putExtra(Constants.COURSE_KEY, courseName);
                startActivityForResult(intent, Constants.COURSE_CODE);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Escolhe o curso seu arrombado", Toast.LENGTH_SHORT).show();
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