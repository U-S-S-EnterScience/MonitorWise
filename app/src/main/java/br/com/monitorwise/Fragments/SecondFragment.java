package br.com.monitorwise.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.monitorwise.Dashboard.DashboardActivity;
import br.com.monitorwise.Monitor.MonitorActivity;
import br.com.monitorwise.R;

/**
 * agosto, 18 2021
 * author: M.Lucas.
 */
public class SecondFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Infla o layout para este fragmento
        View fragmentMonitor = inflater.inflate(R.layout.second_fragment, container, false);

        Button btCreateReport = fragmentMonitor.findViewById(R.id.button_create_report);

        btCreateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MonitorActivity.class));
            }
        });

        return fragmentMonitor;

    }

}
