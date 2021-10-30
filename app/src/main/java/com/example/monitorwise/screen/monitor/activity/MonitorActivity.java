package com.example.monitorwise.screen.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.monitorwise.R;

public class MonitorActivity extends AppCompatActivity implements MonitorContract.View {

    private MonitorContract.ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
    }
}