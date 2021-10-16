package com.example.monitorwise.screen.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.monitorwise.BuildConfig;
import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityHomeBinding;
import com.example.monitorwise.screen.calendar.CalendarFragment;
import com.example.monitorwise.util.Constants;
import com.google.android.material.navigation.NavigationBarView;
import com.google.zxing.Result;

import java.util.Calendar;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private ActivityHomeBinding mBinding;

    private static final String CALENDAR_TAG = "calendar";
    private static final String MONITOR_TAG = "monitor";
    private static final String HISTORIC_TAG = "historic";

    private CodeScanner mCodeScanner;
    boolean CameraPermission = false;
    final int CAMERA_PERM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        this.setUpAppBarToolbar(mBinding.includeToolbar.toolbar, mBinding.includeToolbar.appbar);
        this.showDisplayHomeAsUpEnable(true);
        mBinding.includeToolbar.toolbar.setNavigationIcon(Constants.SHOW_BACK ?
                R.drawable.ic_keyboard_backspace : R.drawable.ic_baseline_qr_code_scanner_24);

        mBinding.includeToolbar.toolbar.setTitle("");

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this,scannerView);

        mBinding.includeToolbar.toolbar.setNavigationOnClickListener(view -> {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
            mCodeScanner.startPreview();
        });

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

        initNavigationBottom();
    }


    private void initNavigationBottom() {
        mBinding.navigationBottom.setOnItemSelectedListener(item -> {

            String tag = null;
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.menu_calendar:
                    tag = CALENDAR_TAG;
                    fragment = getSupportFragmentManager().findFragmentByTag(tag);
                    if (fragment == null) {
                        fragment = CalendarFragment.newInstance();
                    }
                    break;

                case R.id.menu_monitor:
                    break;

                case R.id.menu_historic:
                    break;

            }

            return true;
        });
    }

    private void askPermission(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_PERM);
            }else{
              mCodeScanner.startPreview();
              CameraPermission = true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == CAMERA_PERM){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}