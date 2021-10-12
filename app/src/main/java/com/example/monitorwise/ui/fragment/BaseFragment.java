package com.example.monitorwise.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected ViewDataBinding bindView(LayoutInflater layoutInflater, int layoutId,
                                       @Nullable ViewGroup parent, boolean attachToParent){
        return DataBindingUtil.inflate(layoutInflater, layoutId, parent, attachToParent);
    }

    protected Object bindView(View inflate) {
        return null;
    }
}
