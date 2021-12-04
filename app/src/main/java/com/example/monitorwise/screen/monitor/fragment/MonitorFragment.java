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
import android.widget.Toast;

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
        fetchDisciplineData();
        mBinding.includeContentImageTop.txtFragmentTitle.setText("Monitor");
        mBinding.txtMonitorName.setText(userName);
        mBinding.txtMonitorDisciplineFragment.setText(userDiscipline);
        mBinding.layoutMonitorItem.setOnClickListener(this::validateMonitorData);

        adapter = new MonitorFragmentAdapter(populateItems());
    }

    private void fetchDisciplineData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE);

        String discipline = sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, "");

        switch (discipline) {
            case Constants.ALGORITHM:
                setAlgorithm(sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, ""));
                break;

            case Constants.ENGLISH:
                setEnglish(sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, ""));
                break;

            case Constants.MATHEMATICS:
                setMathematics(sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, ""));
                break;

            case Constants.MICROINFORMATICS:
                setMicroinformatics(sharedPreferences.getString(Constants.USER_DISCIPLINE_KEY, ""));
                break;

        }
    }

    private void setMicroinformatics(String discipline) {
        mBinding.txtMonitorItemDisciplineName.setText(discipline);
        mBinding.txtMonitorItemWeekDay.setText(" Segunda, Quarta e Sexta");
        mBinding.txtMonitorItemHour.setText("16:00 - 18:00");
        mBinding.txtMonitorItemLocal.setText(" https://teams.microsoft.com/l/channel/19%3aYhrKQ3aosUmrS238Ba6h8ed-rznL7339ZpZC59hQaCo1%40thread.tacv2/Geral?groupId=3ce7d56f-91bf-48d5-a729-f2b704ffdba5&tenantId=cf72e2bd-7a2b-4783-bdeb-39d57b07f76f");
    }

    private void setMathematics(String discipline) {
        mBinding.txtMonitorItemDisciplineName.setText(discipline);
        mBinding.txtMonitorItemWeekDay.setText(" Segunda a Sexta");
        mBinding.txtMonitorItemHour.setText("13:00 - 14:00");
        mBinding.txtMonitorItemLocal.setText(" https://teams.microsoft.com/l/channel/19%3aD_Cbr_PsCj2GRspO6B1mxb6pEBK3tP0uDuMbzurraXY1%40thread.tacv2/Geral?groupId=b51f1f35-e9d2-44d0-8709-14ccf333ec3d&tenantId=cf72e2bd-7a2b-4783-bdeb-39d57b07f76f");
    }

    private void setEnglish(String discipline) {
        mBinding.txtMonitorItemDisciplineName.setText(discipline);
        mBinding.txtMonitorItemWeekDay.setText(" Segunda, Terça e Quarta");
        mBinding.txtMonitorItemHour.setText("18:00 - 19:00");
        mBinding.txtMonitorItemLocal.setText(" https://teams.microsoft.com/l/channel/19%3a1e9jazuJMQVTxORWUAsgHOwF3M2rnqhod7a8RuMWU9k1%40thread.tacv2/Geral?groupId=c92db90b-4f39-4eb7-9ad3-675adaeea701&tenantId=cf72e2bd-7a2b-4783-bdeb-39d57b07f76f");
    }

    private void setAlgorithm(String discipline) {
        mBinding.txtMonitorItemDisciplineName.setText(discipline);
        mBinding.txtMonitorItemWeekDay.setText(" Terça, Quinta e Sábado");
        mBinding.txtMonitorItemHour.setText("13:00 - 16:00");
        mBinding.txtMonitorItemLocal.setText(" https://teams.microsoft.com/l/team/19%3ahUo6xwbaDPCphI6ZYeMRMGf_o04VQr5XCV7NHE1zYnE1%40thread.tacv2/conversations?groupId=4176577e-5d00-4fe5-9ad0-af92f19ff7df&tenantId=cf72e2bd-7a2b-4783-bdeb-39d57b07f76f");
    }


    private void validateMonitorData(View view) {
        if (isValid())
            goToMonitorActivity(mBinding.txtMonitorItemDisciplineName.getText().toString());
        else
            showAlert(mBinding.txtMonitorItemDisciplineName.getText().toString());
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
        return true;
    }

    private void goToMonitorActivity(String discipline) {
        Intent intent = new Intent(getContext(), MonitorActivity.class);
        intent.putExtra(Constants.MONITOR_DISCIPLINE_KEY, discipline);
        startActivity(intent);
    }

    private List<Discipline> populateItems() {
        List<Discipline> monitorDisciplines = new ArrayList<>();


        return monitorDisciplines;
    }

    private void fetchUserData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(Constants.USER_SHARED_NAME, Context.MODE_PRIVATE);
        userName = sharedPreferences.getString(Constants.USER_NAME_KEY, "");
        userDiscipline = sharedPreferences.getString(Constants.USER_COURSE_KEY, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}