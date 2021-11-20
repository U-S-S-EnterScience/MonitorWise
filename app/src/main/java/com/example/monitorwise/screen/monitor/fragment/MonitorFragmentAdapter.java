package com.example.monitorwise.screen.monitor.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.monitorwise.R;
import com.example.monitorwise.databinding.MonitorItemBinding;
import com.example.monitorwise.model.domain.discipline.Discipline;

import java.util.List;

/**
 * created by Lucas Mosca on 19/11/2021.
 */
public class MonitorFragmentAdapter extends RecyclerView.Adapter<MonitorFragmentAdapter.MonitorViewHolder> {

    private MonitorFragmentAdapter.OnItemClickListener clickListener;
    private final List<Discipline> disciplines;

    public MonitorFragmentAdapter(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }


    @NonNull
    @Override
    public MonitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        return new MonitorViewHolder(inflate.inflate(R.layout.monitor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MonitorViewHolder holder, int position) {
        holder.mBinding.txtMonitorItemDisciplineName.setText(disciplines.get(position).getName());
        holder.mBinding.txtDays.setText(disciplines.get(position).getDate());
        holder.mBinding.txtHour.setText(disciplines.get(position).getHour());
        holder.mBinding.txtLocal.setText(disciplines.get(position).getLocal());
        holder.bind(holder.getmBinding().txtMonitorItemDisciplineName.getText().toString());
    }

    @Override
    public int getItemCount() {
        return disciplines.size();
    }


    public void setOnClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    class MonitorViewHolder extends RecyclerView.ViewHolder {

        String monitor;

        private MonitorItemBinding mBinding;

        public MonitorViewHolder(@NonNull View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(v -> clickListener.onItemClick(monitor));
        }

        void bind(String monitor) {
            this.monitor = monitor;
        }

        public MonitorItemBinding getmBinding() {
            return mBinding;
        }
    }

    interface OnItemClickListener {
        void onItemClick(String discipline);
    }
}
