package com.example.monitorwise.screen.disciplines;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityDisciplinesBinding;
import com.example.monitorwise.model.domain.discipline.Discipline;
import com.example.monitorwise.screen.user.register.RegisterActivity;
import com.example.monitorwise.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisciplinesActivity extends BaseActivity implements DisciplinesContract.View {

    private ActivityDisciplinesBinding mBinding;
    private DisciplinesContract.ViewModel viewModel;
    private DisciplinesContract.Repository repository;

    private int i = 0x00;

    List<Discipline> disciplines = new ArrayList<>();
    List<String> disciplinesSend = new ArrayList<String>();

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

        adapter.setOnClickListener(new DisciplinesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String discipline) {
                disciplinesSend.add(discipline);
                if (disciplinesSend.size() == 2) {
                    Intent intent = new Intent(DisciplinesActivity.this, RegisterActivity.class);
                    intent.putExtra(Constants.DISCIPLINE_KEY, (Serializable) disciplinesSend);
                    startActivityForResult(intent, Constants.DISCIPLINES_CODE);
                }
            }
        });

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
}