package br.com.monitorwise.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xwray.groupie.Group;
import com.xwray.groupie.GroupAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.monitorwise.Model.CalendarItem;
import br.com.monitorwise.R;

/**
 * agosto, 18 2021
 * author: M.Lucas.
 */
public class FirstFragment extends Fragment {

    private GroupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        RecyclerView rvCalendar = view.findViewById(R.id.recycler_view_calendar);

        adapter = new GroupAdapter();
        rvCalendar.setAdapter(adapter);
        rvCalendar.setLayoutManager(new LinearLayoutManager(view.getContext()));

        populateDisciplines();

        return view;
    }

    private void populateDisciplines(){
        List<CalendarItem> calendarItems = new ArrayList<>();

        calendarItems.add(new CalendarItem("Algoritmo", "Terça-Feira", "18:00h - 19:00h", "Sala 7"));
        calendarItems.add(new CalendarItem("Programação em Microinformática", "Segunda-Feira", "18:00h - 18:30h", "Sala 9"));
        calendarItems.add(new CalendarItem("Arquitetura Computacional", "Quinta-Feira", "17:00h - 18:00h", "Sala 11"));
        calendarItems.add(new CalendarItem("Matemática Discreta", "Sexta-Feira", "18:00h - 19:00h", "Sala 15"));
        calendarItems.add(new CalendarItem("Cálculo", "Quarta-Feira", "18:00h - 19:00h", "Sala 15"));
        calendarItems.add(new CalendarItem("Estatistica Aplicada", "Sábado", "13:00h - 14:00h", "Sala 4"));
        calendarItems.add(new CalendarItem("Linguagem de Programação", "Terça-Feira", "17:00h - 18:00h", "Sala 2"));

        adapter.addAll(calendarItems);
        adapter.notifyDataSetChanged();
    }

}



