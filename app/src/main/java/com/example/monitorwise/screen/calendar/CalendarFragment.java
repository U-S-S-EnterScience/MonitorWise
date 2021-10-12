package com.example.monitorwise.screen.calendar;

import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.FragmentCalendarBinding;
import com.example.monitorwise.ui.fragment.BaseFragment;
import com.example.monitorwise.util.Constants;

import java.util.Calendar;


public class CalendarFragment extends BaseFragment implements CalendarContract {

    private FragmentCalendarBinding mBinding;

    public static CalendarFragment newInstance() {

        CalendarFragment fragment = new CalendarFragment();

        return fragment;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {

        mBinding = FragmentCalendarBinding.inflate(inflater, container, false);
        android.view.View root = mBinding.getRoot();

        onInitView();

        return root;
    }

    private void onInitView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mBinding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}