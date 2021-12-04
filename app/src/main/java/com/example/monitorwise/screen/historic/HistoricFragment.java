package com.example.monitorwise.screen.historic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.FragmentHistoricBinding;
import com.example.monitorwise.screen.historic.month.SelectMonthActivity;
import com.example.monitorwise.ui.fragment.BaseFragment;
import com.example.monitorwise.util.Constants;


public class HistoricFragment extends BaseFragment implements HistoricContract {

    //private HistoricContract.ViewModel viewModel;

    private FragmentHistoricBinding mBinding;

    private int access = 0;


    public HistoricFragment() {
        // Required empty public constructor
    }


    public static HistoricFragment newInstance() {
        HistoricFragment fragment = new HistoricFragment();
        return fragment;
    }


    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {

        mBinding = (FragmentHistoricBinding) this.bindView(
                inflater, R.layout.fragment_historic, container, false);

        onInitView();

        return mBinding.getRoot();
    }

    private void onInitView() {
        fetchInitialData();
        mBinding.includeContentImageTop.txtFragmentTitle.setText("Histórico");
        mBinding.includeContentSelectMonth.layoutSelectMonth.setOnClickListener(v -> startActivity(new Intent(getContext(), SelectMonthActivity.class)));

    }

    private void fetchInitialData() {
        fetchUserData();
        fetchDisciplineData();
        fetchMonthData();
    }

    private void fetchMonitoringData() {
        if (access > 1) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MONITORING_SHARED_NAME, Context.MODE_PRIVATE);

            mBinding.includeContentSearchHistoric.txtHourHistoric.setText(sharedPreferences.getString(Constants.MONITORING_HOUR_KEY, ""));
            mBinding.includeContentSearchHistoric.txtStudentsMediaHistoric.setText(String.valueOf(sharedPreferences.getInt(Constants.MONITORING_STUDENTS_NUMBER_KEY, 0)));
            mBinding.includeContentSearchHistoric.txtMonitorDisciplineHistoric.setText(sharedPreferences.getString(Constants.MONITORING_DISCIPLINE_KEY, ""));
        } else {
            mBinding.includeContentSearchHistoric.txtHourHistoric.setText("");
            mBinding.includeContentSearchHistoric.txtStudentsMediaHistoric.setText("");
            mBinding.includeContentSearchHistoric.txtMonitorDisciplineHistoric.setText("");
        }


    }

    private void fetchDisciplineData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE);
        mBinding.includeContentSelectDisciplineHistoric.textViewChooseCourse.setText(sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, ""));
        mBinding.includeContentSelectDisciplineHistoric.textViewChooseCourse.setTextColor(getResources().getColor(R.color.black));
    }

    private void fetchMonthData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MONTH_SHARED_NAME, Context.MODE_PRIVATE);
        if (access > 1) {
            String month = sharedPreferences.getString(Constants.MONTH_NAME_KEY, "");

            if (month != "") {
                mBinding.includeContentSelectMonth.textViewChooseCourse.setTextColor(getResources().getColor(R.color.black));
                mBinding.includeContentSelectMonth.textViewChooseCourse.setText(month);
            } else
                mBinding.includeContentSelectMonth.textViewChooseCourse.setTextColor(getResources().getColor(R.color.gray));

        }
    }


    private void fetchUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE);
        mBinding.txtMonitorNameHistoric.setText(sharedPreferences.getString(Constants.USER_NAME_KEY, ""));
        mBinding.txtMonitorCourse.setText(sharedPreferences.getString(Constants.USER_PERIOD_KEY, ""));
        mBinding.txtMonitorCourse.setText(sharedPreferences.getString(Constants.USER_COURSE_KEY, ""));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onResume() {
        access++;
        fetchMonthData();
        fetchMonitoringData();
        fetchDisciplineData();
        super.onResume();

    }
}