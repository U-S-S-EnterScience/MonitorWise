package com.example.monitorwise.screen.students.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.ActivityStudentsListBinding;
import com.example.monitorwise.screen.monitor.activity.MonitorActivity;
import com.example.monitorwise.screen.user.login.LoginContract;
import com.example.monitorwise.util.Constants;

public class StudentsListActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityStudentsListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_students_list);
        mBinding.btnAddStudents.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add1:
                mBinding.includeContentCarlos.layoutContentCarlos.setVisibility(View.GONE);
                saveStudentName("Carlos Augusto Hubert", 1);
                break;

            case R.id.img_add2:
                mBinding.includeContentLucas.layoutContentLucas.setVisibility(View.GONE);
                saveStudentName("Lucas Bella Vitta Mosca", 2);
                break;

            case R.id.img_add3:
                mBinding.includeContentLuigi.layoutContentLuigi.setVisibility(View.GONE);
                saveStudentName("Luigi Cavalieri", 3);
                break;

            case R.id.img_add4:
                mBinding.includeContentMateus.layoutContentMateus.setVisibility(View.GONE);
                saveStudentName("Mateus Rocha Pereira", 4);
                break;

            case R.id.img_add5:
                mBinding.includeContentVivian.layoutContentVivian.setVisibility(View.GONE);
                saveStudentName("Vivian Magda S. P. M. de Martino", 5);
                break;
        }
    }

    private void saveStudentName(String studentName, Integer studentKey) {
        SharedPreferences.Editor editor = StudentsListActivity.this.getSharedPreferences(Constants.STUDENT_SHARED_NAME, Context.MODE_PRIVATE).edit();

        switch (studentKey) {
            case 1:
                editor.putString(Constants.STUDENT_1_KEY, studentName).apply();
                break;

            case 2:
                editor.putString(Constants.STUDENT_2_KEY, studentName).apply();
                break;

            case 3:
                editor.putString(Constants.STUDENT_3_KEY, studentName).apply();
                break;

            case 4:
                editor.putString(Constants.STUDENT_4_KEY, studentName).apply();
                break;

            case 5:
                editor.putString(Constants.STUDENT_5_KEY, studentName).apply();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MonitorActivity.class));
        finish();
    }
}