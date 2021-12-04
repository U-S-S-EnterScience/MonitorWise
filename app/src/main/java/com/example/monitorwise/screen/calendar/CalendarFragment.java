package com.example.monitorwise.screen.calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.FragmentCalendarBinding;
import com.example.monitorwise.model.domain.hour.Hour;
import com.example.monitorwise.ui.fragment.BaseFragment;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CalendarFragment extends BaseFragment implements CalendarContract {

    public CalendarContract.ViewModel viewModel;

    private FragmentCalendarBinding mBinding;

    private FirebaseAuth mAuth;

    public static CalendarFragment newInstance() {

        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {

        mBinding = (FragmentCalendarBinding) this.bindView(
                inflater, R.layout.fragment_calendar, container, false);

        onInitView();

        return mBinding.getRoot();
    }

    private void onInitView() {
        mAuth = FirebaseAuth.getInstance();
        mBinding.includeContentImageTop.txtFragmentTitle.setText("Calendário");
        //setUserData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    /*public void setUserData() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("disciplines").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                List list = (List) task.getResult().getValue();

                for (int n = 0; n < list.size(); n++) {
                    HashMap<String, Object> map = (HashMap<String, Object>) list.get(n);
                    if(map.get("id_monitor").equals(mAuth.getUid())) {
                        String disciplineName = map.get("name").toString();
                        List date = (List) map.get("date");
                        List temp = (List) map.get("hour");
                        List<Hour> hours = new ArrayList<Hour>();

                        for(int num = 0; num < temp.size(); num++) {
                            HashMap<String, String> tempMap = (HashMap<String, String>) temp.get(num);
                            hours.add(new Hour(tempMap.get("begin"), tempMap.get("finish")));
                        }

                        for (int num = 0; num < hours.size(); num++) {
                            Log.d(date.get(num).toString(), hours.get(num).getBegin() + " - " + hours.get(num).getFinish());
                        }
                    }
                }
            }
        });
    }*/
}