package com.example.monitorwise.screen.monitor.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.monitorwise.R;
import com.example.monitorwise.databinding.FragmentMonitorBinding;
import com.example.monitorwise.model.domain.discipline.Discipline;
import com.example.monitorwise.screen.monitor.activity.MonitorActivity;
import com.example.monitorwise.ui.fragment.BaseFragment;
import com.example.monitorwise.util.Constants;

import java.util.ArrayList;
import java.util.List;


public class MonitorFragment extends BaseFragment implements MonitorContract.View {

    private FragmentMonitorBinding mBinding;
    //private MonitorContract.ViewModel viewModel;
    //private MonitorContract.Repository repository;

    private List<Discipline> disciplines = new ArrayList<>();

    MonitorFragmentAdapter adapter = new MonitorFragmentAdapter(disciplines);

    private String userName;
    private String userDiscipline;

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
        fetchUserData();
        mBinding.txtMonitorName.setText(userName);
        mBinding.txtRegistration.setText(userDiscipline);

        adapter = new MonitorFragmentAdapter(populateItems());
        mBinding.rvMonitorDisciplines.setAdapter(adapter);
        adapter.setOnClickListener(this::validateMonitorData);
    }

    private void validateMonitorData(String discipline) {
        if (isValid())
            goToMonitorActivity(discipline);
        else
            showAlert(discipline);

    }

    private void showAlert(String discipline) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Ocorreu um erro ao tentar iniciar a monitoria da disciplina: " + discipline + "." +
                "\n Por favor verifique a data e hora do início desta disciplina.")
                .setPositiveButton(R.string.confirm, (dialog, id) -> {
                });
        builder.create().show();
    }

    private boolean isValid() {
        // validar dia e horário
        // validar localização ou qr code
        //validar o karalho a 4 antes de redirecionar para a MonitorActivity
        return false;
    }

    private void goToMonitorActivity(String discipline) {
        Intent intent = new Intent(getContext(), MonitorActivity.class);
        intent.putExtra(Constants.MONITOR_DISCIPLINE_KEY, discipline);
        startActivity(intent);
    }

    private List<Discipline> populateItems() {
        List<Discipline> monitorDisciplines = new ArrayList<>();

        //monitorDisciplines.add(new Discipline("Algoritmo", "Segunda-Feira a Sexta-Feira", "link da sala do teams", "17:40 - 19:00"));

        return monitorDisciplines;
    }

    private void fetchUserData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE);
        userName = sharedPreferences.getString(Constants.USER_NAME_KEY, "");
        userDiscipline = sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}