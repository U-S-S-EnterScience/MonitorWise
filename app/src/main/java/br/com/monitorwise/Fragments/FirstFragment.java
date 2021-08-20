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

import java.util.Calendar;

import br.com.monitorwise.R;

/**
 * agosto, 18 2021
 * author: M.Lucas.
 */
public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        RecyclerView rvCalendar = view.findViewById(R.id.recycler_view_calendar);

        rvCalendar.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CalendarAdapter calendarAdapter = new CalendarAdapter();
        rvCalendar.setAdapter(calendarAdapter);

        return view;
    }

}



