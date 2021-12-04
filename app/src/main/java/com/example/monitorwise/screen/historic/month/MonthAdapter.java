package com.example.monitorwise.screen.historic.month;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.DisciplineChooseItemBinding;
import com.example.monitorwise.model.domain.month.Month;

import java.util.List;

/**
 * created by Lucas Mosca on 01/12/2021.
 */
public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {

    private MonthAdapter.OnItemClickListener clickListener;
    private final List<Month> months;

    public MonthAdapter(List<Month> months) {
        this.months = months;
    }


    @NonNull
    @Override
    public MonthAdapter.MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MonthViewHolder(inflater.inflate(R.layout.discipline_choose_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MonthAdapter.MonthViewHolder holder, int position) {
        holder.mBinding.txtDisciplineItemTitle.setText(months.get(position).getName());
        holder.bind(holder.getmBinding().txtDisciplineItemTitle.getText().toString());

    }

    @Override
    public int getItemCount() {
        return months.size();
    }


    public void setOnClickListener(MonthAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class MonthViewHolder extends RecyclerView.ViewHolder {
        String month;

        private DisciplineChooseItemBinding mBinding;

        public MonthViewHolder(@NonNull View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(view -> clickListener.onItemClick(month));
        }

        void bind(String month) {
            this.month = month;
        }

        public DisciplineChooseItemBinding getmBinding() {
            return mBinding;
        }
    }

    interface OnItemClickListener {
        void onItemClick(String discipline);
    }
}
