package com.example.monitorwise.screen.monitor.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.FragmentMonitorBinding;
import com.example.monitorwise.ui.fragment.BaseFragment;


public class MonitorFragment extends BaseFragment {

    private FragmentMonitorBinding mBinding;

    public static MonitorFragment newInstance() {

        MonitorFragment fragment = new MonitorFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = (FragmentMonitorBinding) this.bindView(
                inflater, R.layout.fragment_monitor, container, false);

        onInitView();

        return mBinding.getRoot();
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