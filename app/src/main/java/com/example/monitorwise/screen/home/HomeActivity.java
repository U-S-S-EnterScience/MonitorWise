package com.example.monitorwise.screen.home;


import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.monitorwise.R;
import com.example.monitorwise.base.BaseActivity;
import com.example.monitorwise.databinding.ActivityHomeBinding;
import com.example.monitorwise.screen.calendar.CalendarFragment;
import com.example.monitorwise.screen.historic.HistoricFragment;
import com.example.monitorwise.screen.monitor.fragment.MonitorFragment;
import com.example.monitorwise.util.Constants;

import com.google.firebase.auth.FirebaseAuth;


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