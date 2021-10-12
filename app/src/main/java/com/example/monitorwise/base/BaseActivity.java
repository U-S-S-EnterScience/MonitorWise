package com.example.monitorwise.base;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {

    }

    public void setUpToolBar(Toolbar toolBar) {
        this.setSupportActionBar(toolBar);
    }

    public void setUpAppBarToolbar(Toolbar toolbar, AppBarLayout appBarLayout) {
        this.setSupportActionBar(toolbar);
    }

    public void showDisplayHomeAsUpEnable(boolean showHomeAsUp) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }
}
