package com.example.monitorwise.screen.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.ActivityMonitorBinding;
import com.example.monitorwise.util.Constants;

public class MonitorActivity extends AppCompatActivity implements MonitorContract.View {

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
        mBinding.includeContentPresenceList.presenceLayout.setOnClickListener(this::presenceClick);

    }

    private void presenceClick(View view) {
        Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        mBinding.includeContentDisciplineName.txtMonitorDiscipline.setText(intent.getStringExtra(Constants.MONITOR_DISCIPLINE_KEY));

    }
}