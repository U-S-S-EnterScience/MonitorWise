package com.example.monitorwise.screen.home;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityHomeBinding;
import com.example.monitorwise.model.domain.account.Account;
import com.example.monitorwise.screen.calendar.CalendarFragment;
import com.example.monitorwise.screen.historic.HistoricFragment;
import com.example.monitorwise.screen.monitor.fragment.MonitorFragment;
import com.example.monitorwise.screen.user.login.LoginActivity;
import com.example.monitorwise.util.Constants;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeActivity extends BaseActivity implements HomeContract.View {

    private ActivityHomeBinding mBinding;
    public HomeContract.ViewModel viewModel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInitView();
    }

    private void onInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        setUserData();

        getSupportFragmentManager().beginTransaction().add(R.id.container, CalendarFragment.newInstance()).commit();

        this.setUpAppBarToolbar(mBinding.includeToolbar.toolbar, mBinding.includeToolbar.appbar);
        this.showDisplayHomeAsUpEnable(true);
        mBinding.includeToolbar.toolbar.setNavigationIcon(Constants.SHOW_BACK ?
                R.drawable.ic_toolbar : R.drawable.ic_toolbar);

        mBinding.includeToolbar.toolbar.setNavigationOnClickListener(view -> {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
        });

        initNavigationBottom();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.icon_logout) {
            showLogoutMessage();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    private void showLogoutMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Deseja sair deste usuário?")
                .setPositiveButton(R.string.exit, (dialog, id) -> {
                    logout();
                })
                .setNegativeButton(R.string.cancelar, (dialog, id) -> {
                });
        builder.create().show();
    }

    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(
                HomeActivity.this,
                LoginActivity.class)
        );
    }

    private void saveDisciplines(String disciplineName) {
        SharedPreferences.Editor editor = HomeActivity.this.getSharedPreferences(Constants.DISCIPLINE_SHARED_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(Constants.DISCIPLINE_NAME, disciplineName).apply();

    }

    private void setUserData() {
        SharedPreferences.Editor editor = HomeActivity.this.getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE).edit();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot users : dataSnapshot.getChildren()) {
                    if (users.getKey().equals(mAuth.getUid())) {
                        Account user = users.getValue(Account.class);
                        editor.putString(Constants.USER_NAME_KEY, user.getFullName());
                        editor.putString(Constants.USER_EMAIL_KEY, user.getEmail());
                        editor.putString(Constants.USER_COURSE_KEY, user.getCourse());
                        editor.putString(Constants.USER_DISCIPLINE_KEY, user.getClassName());
                        editor.putString(Constants.USER_PERIOD_KEY, user.getPeriod());
                        editor.apply();
                        saveDisciplines(user.getClassName());
                        mBinding.includeToolbar.txtUserEmail.setText(user.getEmail());
                        mBinding.includeToolbar.txtUserName.setText(user.getFullName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
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


            return true;
        });
    }

}