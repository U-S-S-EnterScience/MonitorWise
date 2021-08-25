package br.com.monitorwise.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwray.groupie.GroupAdapter;


import java.util.ArrayList;
import java.util.List;

import br.com.monitorwise.Model.CalendarItem;
import br.com.monitorwise.Model.MonitorItem;
import br.com.monitorwise.Monitor.MonitorActivity;
import br.com.monitorwise.R;

/**
 * agosto, 18 2021
 * author: M.Lucas.
 */
public class SecondFragment extends Fragment {

    private GroupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Infla o layout para este fragmento
        View fragmentMonitor = inflater.inflate(R.layout.second_fragment, container, false);

        Button btCreateReport = fragmentMonitor.findViewById(R.id.button_create_report);

        RecyclerView rvMonitorDisciplines = fragmentMonitor.findViewById(R.id.recycler_view_monitor_monitories);


        adapter = new GroupAdapter();
        rvMonitorDisciplines.setAdapter(adapter);
        rvMonitorDisciplines.setLayoutManager(new LinearLayoutManager(getContext()));

        populateDisciplines();

        btCreateReport.setOnClickListener(v -> startActivity(new Intent(getContext(), MonitorActivity.class)));

        return fragmentMonitor;

    }

    private void populateDisciplines(){
        List<MonitorItem> monitorItem = new ArrayList<>();

        monitorItem.add(new MonitorItem("Algoritmo", "Terça-Feira", "18:00h - 19:00h", "Sala 7"));
        monitorItem.add(new MonitorItem("Programação em Microinformática", "Segunda-Feira", "18:00h - 18:30h", "Sala 9"));

        adapter.addAll(monitorItem);
        adapter.notifyDataSetChanged();
    }

}
