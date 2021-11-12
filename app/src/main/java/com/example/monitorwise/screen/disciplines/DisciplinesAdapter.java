package com.example.monitorwise.screen.disciplines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.DisciplineChooseItemBinding;
import com.example.monitorwise.model.domain.discipline.Discipline;

import java.util.List;

/**
 * created by Lucas Mosca on 10/11/2021.
 */
public class DisciplinesAdapter extends RecyclerView.Adapter<DisciplinesAdapter.DisciplinesViewHolder> {

    private OnItemClickListener clickListener;
    private final List<Discipline> disciplines;

    public DisciplinesAdapter(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @NonNull
    @Override
    public DisciplinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new DisciplinesViewHolder(inflater.inflate(R.layout.discipline_choose_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinesViewHolder holder, int position) {
        holder.mBinding.txtDisciplineItemTitle.setText(disciplines.get(position).getName());
        holder.bind(holder.getmBinding().txtDisciplineItemTitle.getText().toString());
    }

    @Override
    public int getItemCount() {
        return disciplines.size();
    }

    public void setOnClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class DisciplinesViewHolder extends RecyclerView.ViewHolder {
        String discipline;

        private DisciplineChooseItemBinding mBinding;

        public DisciplinesViewHolder(@NonNull View itemView) {
            super(itemView);

            mBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(discipline);
                }
            });
        }

        void bind(String discipline) {
            this.discipline = discipline;
        }

        public DisciplineChooseItemBinding getmBinding() {
            return mBinding;
        }
    }

    interface OnItemClickListener {
        void onItemClick(String discipline);
    }
}
