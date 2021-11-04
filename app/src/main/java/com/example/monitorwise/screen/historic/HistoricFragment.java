package com.example.monitorwise.screen.historic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.FragmentHistoricBinding;
import com.example.monitorwise.ui.fragment.BaseFragment;


public class HistoricFragment extends BaseFragment implements HistoricContract {

    private HistoricContract.ViewModel viewModel;

    private FragmentHistoricBinding mBinding;


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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mBinding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}