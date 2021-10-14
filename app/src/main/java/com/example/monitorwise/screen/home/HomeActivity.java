package com.example.monitorwise.screen.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.monitorwise.BuildConfig;
import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityHomeBinding;
import com.example.monitorwise.screen.calendar.CalendarFragment;
import com.example.monitorwise.screen.user.login.LoginActivity;
import com.example.monitorwise.util.Constants;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private ActivityHomeBinding mBinding;
    private FirebaseAuth mAuth;

    private static final String CALENDAR_TAG = "calendar";
    private static final String MONITOR_TAG = "monitor";
    private static final String HISTORIC_TAG = "historic";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        this.setUpAppBarToolbar(mBinding.includeToolbar.toolbar, mBinding.includeToolbar.appbar);
        this.showDisplayHomeAsUpEnable(true);
        mBinding.includeToolbar.toolbar.setNavigationIcon(Constants.SHOW_BACK ?
                R.drawable.ic_keyboard_backspace : R.drawable.ic_baseline_qr_code_scanner_24);

        mBinding.includeToolbar.toolbar.setTitle("");

        mBinding.includeToolbar.toolbar.setNavigationOnClickListener(view -> {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
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
                    mAuth.signOut();
                    startActivity(new Intent(
                            HomeActivity.this,
                            LoginActivity.class)
                    );
                    break;
            }

            return true;
        });
    }

}