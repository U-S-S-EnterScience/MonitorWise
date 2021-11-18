package com.example.monitorwise.screen.disciplines;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityDisciplinesBinding;
import com.example.monitorwise.model.domain.discipline.Discipline;
import com.example.monitorwise.screen.user.register.RegisterActivity;
import com.example.monitorwise.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DisciplinesActivity extends BaseActivity implements DisciplinesContract.View {

    private ActivityDisciplinesBinding mBinding;
    //private DisciplinesContract.ViewModel viewModel;
    //private DisciplinesContract.Repository repository;

    List<Discipline> disciplines = new ArrayList<>();

    DisciplinesAdapter adapter = new DisciplinesAdapter(disciplines);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_disciplines);
        mBinding.rvListDiscipline.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DisciplinesAdapter(populateItems());
        mBinding.rvListDiscipline.setAdapter(adapter);

        adapter.setOnClickListener(this::showAlertDialog);

    }

    private void saveDisciplineData(String discipline) {
        SharedPreferences.Editor editor = DisciplinesActivity.this.getSharedPreferences(Constants.REGISTER_SHARED_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.REGISTER_DISCIPLINE_KEY, discipline).apply();
        goToRegister();
    }

    private void showAlertDialog(String discipline) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Você escolheu a disciplina: " + discipline + ".")
                .setPositiveButton(R.string.confirm, (dialog, id) -> {
                    saveDisciplineData(discipline);
                })
                .setNegativeButton(R.string.cancelar, (dialog, id) -> {
                });
        builder.create().show();

    }

    private void goToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    private List<Discipline> populateItems() {
        List<Discipline> fatecDisciplines = new ArrayList<>();

        fatecDisciplines.add(new Discipline("Algoritmo"));
        fatecDisciplines.add(new Discipline("Programação em Microinformática"));
        fatecDisciplines.add(new Discipline("Arquitetura Computacional"));
        fatecDisciplines.add(new Discipline("Matemática Discreta"));
        fatecDisciplines.add(new Discipline("Cálculo"));
        fatecDisciplines.add(new Discipline("Estatística Aplicada"));
        fatecDisciplines.add(new Discipline("Linguagem de Programação"));

        return fatecDisciplines;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}