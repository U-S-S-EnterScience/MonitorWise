package com.example.monitorwise.screen.monitor.activity;

import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.monitorwise.BuildConfig;
import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityMonitorBinding;
import com.example.monitorwise.screen.students.list.StudentsListActivity;
import com.example.monitorwise.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MonitorActivity extends BaseActivity implements MonitorContract.View {

    private ActivityMonitorBinding mBinding;

    //private MonitorContract.ViewModel viewModel;
    //private MonitorContract.Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_monitor);

        this.setUpAppBarToolbar(mBinding.includeToolbar.toolbar, mBinding.includeToolbar.appbar);
        this.showDisplayHomeAsUpEnable(true);
        mBinding.includeToolbar.toolbar.setNavigationIcon(Constants.SHOW_BACK ?
                R.drawable.ic_keyboard_backspace : R.drawable.ic_keyboard_backspace);


        mBinding.includeToolbar.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        fetchStudents();
        fetchDiscipline();
        refreshLayout();

        mBinding.includeContentStudentsList.layoutStudentList.setVisibility(View.VISIBLE);

        mBinding.includeContentPresenceItem.presenceLayout.setOnClickListener(v -> goToStudentList());

        mBinding.btnFinish.setOnClickListener(v -> saveMonitoring());

        mBinding.includeContentStudentsList.imgStudent1.setOnClickListener(v -> hideStudent1());
        mBinding.includeContentStudentsList.imgStudent2.setOnClickListener(v -> hideStudent2());
        mBinding.includeContentStudentsList.imgStudent3.setOnClickListener(v -> hideStudent3());
        mBinding.includeContentStudentsList.imgStudent4.setOnClickListener(v -> hideStudent4());
        mBinding.includeContentStudentsList.imgStudent5.setOnClickListener(v -> hideStudent5());
    }

    private void fetchDiscipline() {
        SharedPreferences sharedPreferences = MonitorActivity.this.getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE);
        mBinding.includeContentDisciplineName.txtMonitorDiscipline.setText(sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, ""));
    }

    private void saveMonitoring() {
        if (isRecordable()) {
            alertSaveMonitoring();
        } else {
            showAlert();
        }
    }

    private void alertSaveMonitoring() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Finalizar a Monitoria");
        builder.setMessage("Deseja salvar os dados da monitoria?")
                .setPositiveButton(R.string.save, (dialog, id) -> {
                    saveMonitoringData();
                });
        builder.create().show();
    }

    private void saveMonitoringData() {
        SharedPreferences.Editor editor = MonitorActivity.this.getSharedPreferences(Constants.MONITORING_SHARED_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.MONITORING_STUDENTS_KEY, fetchStudentsName().toString());
        editor.putString(Constants.MONITORING_SUBJECT_KEY, mBinding.includeContentSubject.edtSubject.getText().toString());
        editor.putString(Constants.MONITORING_DISCIPLINE_KEY, mBinding.includeContentDisciplineName.txtMonitorDiscipline.getText().toString());
        editor.putInt(Constants.MONITORING_STUDENTS_NUMBER_KEY, studentsNumber());
        editor.putString(Constants.MONITORING_HOUR_KEY, "1:00h");
        editor.apply();
        Toast.makeText(this, "Monitoria Salva", Toast.LENGTH_SHORT).show();
        finish();
    }

    private int studentsNumber() {
        SharedPreferences sharedPreferences = MonitorActivity.this.getSharedPreferences(Constants.STUDENT_SHARED_NAME, Context.MODE_PRIVATE);

        String student1 = sharedPreferences.getString(Constants.STUDENT_1_KEY, "");
        String student2 = sharedPreferences.getString(Constants.STUDENT_2_KEY, "");
        String student3 = sharedPreferences.getString(Constants.STUDENT_3_KEY, "");
        String student4 = sharedPreferences.getString(Constants.STUDENT_4_KEY, "");
        String student5 = sharedPreferences.getString(Constants.STUDENT_5_KEY, "");

        int studentsNumber = 0;

        if (!student1.equals("")) {
            studentsNumber++;
        }

        if (!student2.equals("")) {
            studentsNumber++;
        }

        if (!student3.equals("")) {
            studentsNumber++;
        }

        if (!student4.equals("")) {
            studentsNumber++;
        }

        if (!student5.equals("")) {
            studentsNumber++;
        }

        return studentsNumber;

    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirme os Dados da Monitoria");
        builder.setMessage("Por favor verifique os dados da monitoria.\n" + "Depois, clique em finalizar para salvar os dados.")
                .setPositiveButton(R.string.confirm, (dialog, id) -> {
                });
        builder.create().show();
    }

    private List<String> fetchStudentsName() {
        SharedPreferences sharedPreferences = MonitorActivity.this.getSharedPreferences(Constants.STUDENT_SHARED_NAME, Context.MODE_PRIVATE);

        ArrayList<String> studentsName = new ArrayList<>();


        String student1 = sharedPreferences.getString(Constants.STUDENT_1_KEY, "");
        String student2 = sharedPreferences.getString(Constants.STUDENT_2_KEY, "");
        String student3 = sharedPreferences.getString(Constants.STUDENT_3_KEY, "");
        String student4 = sharedPreferences.getString(Constants.STUDENT_4_KEY, "");
        String student5 = sharedPreferences.getString(Constants.STUDENT_5_KEY, "");


        if (!student1.equals("")) {
            studentsName.add(student1);
        }

        if (!student2.equals("")) {
            studentsName.add(student2);
        }

        if (!student3.equals("")) {
            studentsName.add(student3);
        }

        if (!student4.equals("")) {
            studentsName.add(student4);
        }

        if (!student5.equals("")) {
            studentsName.add(student5);
        }

        return studentsName;
    }


    private boolean isRecordable() {
        // validar se todos os dados da monitoria estão ok
        return true;
    }


    private void hideStudent1() {
        mBinding.includeContentStudentsList.txtStudentName1.setVisibility(View.GONE);
        mBinding.includeContentStudentsList.imgStudent1.setVisibility(View.GONE);
        deleteStudent(1);
        refreshLayout();
    }


    private void hideStudent2() {
        mBinding.includeContentStudentsList.txtStudentName2.setVisibility(View.GONE);
        mBinding.includeContentStudentsList.imgStudent2.setVisibility(View.GONE);
        deleteStudent(2);
        refreshLayout();
    }


    private void hideStudent3() {
        mBinding.includeContentStudentsList.txtStudentName3.setVisibility(View.GONE);
        mBinding.includeContentStudentsList.imgStudent3.setVisibility(View.GONE);
        deleteStudent(3);
        refreshLayout();
    }


    private void hideStudent4() {
        mBinding.includeContentStudentsList.txtStudentName4.setVisibility(View.GONE);
        mBinding.includeContentStudentsList.imgStudent4.setVisibility(View.GONE);
        deleteStudent(4);
        refreshLayout();
    }


    private void hideStudent5() {
        mBinding.includeContentStudentsList.txtStudentName5.setVisibility(View.GONE);
        mBinding.includeContentStudentsList.imgStudent5.setVisibility(View.GONE);
        deleteStudent(5);
        refreshLayout();
    }


    private void deleteStudent(int studentPosition) {
        SharedPreferences.Editor editor = MonitorActivity.this.getSharedPreferences(Constants.STUDENT_SHARED_NAME, Context.MODE_PRIVATE).edit();

        switch (studentPosition) {
            case 1:
                editor.putString(Constants.STUDENT_1_KEY, "").apply();
                break;
            case 2:
                editor.putString(Constants.STUDENT_2_KEY, "").apply();
                break;
            case 3:
                editor.putString(Constants.STUDENT_3_KEY, "").apply();
                break;
            case 4:
                editor.putString(Constants.STUDENT_4_KEY, "").apply();
                break;
            case 5:
                editor.putString(Constants.STUDENT_5_KEY, "").apply();
                break;
        }
    }


    private void refreshLayout() {
        SharedPreferences sharedPreferences = MonitorActivity.this.getSharedPreferences(Constants.STUDENT_SHARED_NAME, Context.MODE_PRIVATE);
        String student1 = sharedPreferences.getString(Constants.STUDENT_1_KEY, "");
        String student2 = sharedPreferences.getString(Constants.STUDENT_2_KEY, "");
        String student3 = sharedPreferences.getString(Constants.STUDENT_3_KEY, "");
        String student4 = sharedPreferences.getString(Constants.STUDENT_4_KEY, "");
        String student5 = sharedPreferences.getString(Constants.STUDENT_5_KEY, "");


        if (student1.equals("") && student2.equals("") && student3.equals("") && student4.equals("") && student5.equals("")) {
            mBinding.includeContentStudentsList.layoutStudentList.setVisibility(View.GONE);
            mBinding.includeContentStudentsList.cvStudentList.setVisibility(View.GONE);
        } else {
            mBinding.includeContentStudentsList.layoutStudentList.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.cvStudentList.setVisibility(View.VISIBLE);
        }

    }


    private void fetchStudents() {
        SharedPreferences sharedPreferences = MonitorActivity.this.getSharedPreferences(Constants.STUDENT_SHARED_NAME, Context.MODE_PRIVATE);

        loadStudentsData(sharedPreferences.getString(Constants.STUDENT_1_KEY, ""),
                sharedPreferences.getString(Constants.STUDENT_2_KEY, ""),
                sharedPreferences.getString(Constants.STUDENT_3_KEY, ""),
                sharedPreferences.getString(Constants.STUDENT_4_KEY, ""),
                sharedPreferences.getString(Constants.STUDENT_5_KEY, ""));
    }


    private void loadStudentsData(String student1, String student2, String student3, String student4, String student5) {

        if (!student1.isEmpty()) {
            mBinding.includeContentStudentsList.txtStudentName1.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.imgStudent1.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.txtStudentName1.setText(student1);
        } else {
            mBinding.includeContentStudentsList.txtStudentName1.setVisibility(View.GONE);
            mBinding.includeContentStudentsList.imgStudent1.setVisibility(View.GONE);
        }


        if (!student2.isEmpty()) {
            mBinding.includeContentStudentsList.txtStudentName2.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.imgStudent2.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.txtStudentName2.setText(student2);
        } else {
            mBinding.includeContentStudentsList.txtStudentName2.setVisibility(View.GONE);
            mBinding.includeContentStudentsList.imgStudent2.setVisibility(View.GONE);
        }


        if (!student3.isEmpty()) {
            mBinding.includeContentStudentsList.txtStudentName3.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.imgStudent3.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.txtStudentName3.setText(student3);
        } else {
            mBinding.includeContentStudentsList.txtStudentName3.setVisibility(View.GONE);
            mBinding.includeContentStudentsList.imgStudent3.setVisibility(View.GONE);
        }


        if (!student4.isEmpty()) {
            mBinding.includeContentStudentsList.txtStudentName4.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.imgStudent4.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.txtStudentName4.setText(student4);
        } else {
            mBinding.includeContentStudentsList.txtStudentName4.setVisibility(View.GONE);
            mBinding.includeContentStudentsList.imgStudent4.setVisibility(View.GONE);
        }


        if (!student5.isEmpty()) {
            mBinding.includeContentStudentsList.txtStudentName5.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.imgStudent5.setVisibility(View.VISIBLE);
            mBinding.includeContentStudentsList.txtStudentName5.setText(student5);
        } else {
            mBinding.includeContentStudentsList.txtStudentName5.setVisibility(View.GONE);
            mBinding.includeContentStudentsList.imgStudent5.setVisibility(View.GONE);
        }

    }


    private void goToStudentList() {
        startActivity(new Intent(this, StudentsListActivity.class));
        finish();
    }


    private void showStudentsList() {
        startActivity(new Intent(this, StudentsListActivity.class));
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nenhum dado será salvo");
        builder.setMessage("Atenção. Se confirmar agora, nenhum dado será salvo.\n" + "Para salvar, clique em finalizar")
                .setPositiveButton(R.string.confirm, (dialog, id) -> super.onBackPressed())
                .setNegativeButton(R.string.cancelar, (dialog, id) -> {
                });
        builder.create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDiscipline();
    }
}