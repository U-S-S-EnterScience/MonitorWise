package com.example.monitorwise.screen.home;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityHomeBinding;
import com.example.monitorwise.model.domain.account.Account;
import com.example.monitorwise.model.domain.discipline.Discipline;
import com.example.monitorwise.screen.calendar.CalendarFragment;
import com.example.monitorwise.screen.historic.HistoricFragment;
import com.example.monitorwise.screen.monitor.fragment.MonitorFragment;
import com.example.monitorwise.screen.user.login.LoginActivity;
import com.example.monitorwise.screen.user.register.RegisterActivity;
import com.example.monitorwise.util.Constants;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class HomeActivity extends BaseActivity implements HomeContract.View {

    private ActivityHomeBinding mBinding;
    public HomeContract.ViewModel viewModel;
    private FirebaseAuth mAuth;

    private Boolean mBackClicked;

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

        // Neste método deve puxar todas as informações do usuário para salvar no banco local
        setUserData();

        getSupportFragmentManager().beginTransaction().add(R.id.container, CalendarFragment.newInstance()).commit();

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

    private void setUserData() {
        SharedPreferences.Editor editor = HomeActivity.this.getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE).edit();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    if(users.getKey().equals(mAuth.getUid())) {
                        Account user = users.getValue(Account.class);
                        editor.putString(Constants.USER_NAME_KEY, user.getFullName());
                        editor.putString(Constants.USER_EMAIL_KEY, user.getEmail());
                        editor.putString(Constants.USER_COURSE_KEY, user.getCourse());
                        editor.putString(Constants.USER_DISCIPLINE_KEY, user.getClassName());
                        editor.putString(Constants.USER_PERIOD_KEY, user.getPeriod());
                        editor.apply();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);

        /*if (view.getId() == R.id.btn_logout) {
            mAuth.signOut();
            startActivity(new Intent(
                    HomeActivity.this,
                    LoginActivity.class)
            );

        }*/
    }

    private void initNavigationBottom() {
        mBinding.navigationBottom.setOnItemSelectedListener(item -> {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            switch (item.getItemId()) {

                case R.id.menu_calendar:
                    ft.replace(R.id.container, CalendarFragment.newInstance());
                    ft.commit();
                    break;

                case R.id.menu_monitor:
                    ft.replace(R.id.container, MonitorFragment.newInstance());
                    ft.commit();
                    break;

                case R.id.menu_historic:
                    ft.replace(R.id.container, HistoricFragment.newInstance());
                    ft.commit();
                    break;

                //mAuth.signOut();
                //startActivity(new Intent(
                //        HomeActivity.this,
                //        LoginActivity.class)
                //);

            }

            /*if (fragment != null && getSupportFragmentManager() != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    if (tag.equals((getLastBackStackTag()))) {
                        return true;
                    }
                    transaction.hide(getSupportFragmentManager().findFragmentByTag(getLastBackStackTag()));
                }

                if (!fragment.isAdded()) {
                    transaction.add(R.id.container, fragment, tag);
                } else {
                    transaction.show(getSupportFragmentManager().findFragmentByTag(tag));
                }
            }*/
            return true;
        });
    }

/*    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            mBackClicked = true;

            getSupportFragmentManager().popBackStackImmediate();
            selectItemNavigationBottom();
        } else {
            finish();
            super.onBackPressed();
        }
    }*/

    /*private void selectItemNavigationBottom() {
        String tag = getLastBackStackTag();

        int index;

        switch (tag) {
            case MONITOR_TAG:
                index = R.id.menu_monitor;
                break;
            case HISTORIC_TAG:
                index = R.id.menu_historic;
                break;
            default:
                index = R.id.menu_calendar;
        }
        mBinding.navigationBottom.setSelectedItemId(index);
    }*/

    /*private String getLastBackStackTag() {
        return getSupportFragmentManager().getBackStackEntryCount() > 0 ?
                getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager()
                        .getBackStackEntryCount() - 1).getName() : CALENDAR_TAG;
    }*/

    /*private Boolean hasFragment() {
        return getSupportFragmentManager().findFragmentByTag(CALENDAR_TAG) != null;
    }*/

    /*private FragmentManager.OnBackStackChangedListener onBackStackChangedListener = () -> {
        String lastTag = getLastBackStackTag();
        if ((CALENDAR_TAG.equals(lastTag) || MONITOR_TAG.equals(lastTag)) && hasFragment()) {
            viewModel.displayLocationSettingsRequest();
        }
    };*/

}