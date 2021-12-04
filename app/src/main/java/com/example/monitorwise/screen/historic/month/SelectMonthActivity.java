package com.example.monitorwise.screen.historic.month;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.ActivitySelectMonthBinding;
import com.example.monitorwise.model.domain.discipline.Discipline;
import com.example.monitorwise.model.domain.month.Month;
import com.example.monitorwise.screen.disciplines.DisciplinesAdapter;
import com.example.monitorwise.util.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectMonthActivity extends AppCompatActivity {

    private ActivitySelectMonthBinding mBinding;

    List<Month> months = new ArrayList<>();
    MonthAdapter adapter = new MonthAdapter(months);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_month);
        mBinding.rvListMonth.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MonthAdapter(populateItems());
        mBinding.rvListMonth.setAdapter(adapter);
        adapter.setOnClickListener(this::saveMonth);
    }

    private void showAlertDialog(String month) {
        saveMonth(month);
        Toast.makeText(this, month, Toast.LENGTH_SHORT).show();
    }

    private void saveMonth(String month) {
        SharedPreferences.Editor editor = SelectMonthActivity.this.getSharedPreferences(Constants.MONTH_SHARED_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.MONTH_NAME_KEY, month).apply();
        finish();
    }

    private List<Month> populateItems() {
        List<Month> months = new ArrayList<>();

        months.add(new Month("Agosto"));
        months.add(new Month("Setembro"));
        months.add(new Month("Outubro"));
        months.add(new Month("Novembro"));
        months.add(new Month("Dezembro"));

        return months;
    }
}