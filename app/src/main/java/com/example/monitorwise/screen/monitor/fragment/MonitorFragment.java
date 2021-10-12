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


public class MonitorFragment extends Fragment {

    private FragmentMonitorBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = FragmentMonitorBinding.inflate(inflater, container, false);
        View root = mBinding.getRoot();

        onInitView();

        return root;

    }

    private void onInitView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mBinding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false);
    }
}