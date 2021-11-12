package com.example.monitorwise.screen.courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorwise.R;
import com.example.monitorwise.databinding.CourseItemBinding;
import com.example.monitorwise.model.domain.course.Course;

import java.util.List;

/**
 * created by Lucas Mosca on 09/11/2021.
 */
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    // o adapter espera uma lista de dados prontos (fatecCourses)

    private OnItemClickListener clickListener;
    private final List<Course> fatecCourses;

    public CoursesAdapter(List<Course> fatecCourses) {
        this.fatecCourses = fatecCourses;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CoursesViewHolder(inflater.inflate(R.layout.course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        holder.mBinding.txtCourseItemTitle.setText(fatecCourses.get(position).getName());
        holder.bind(holder.getmBinding().txtCourseItemTitle.getText().toString());
    }

    @Override
    public int getItemCount() {
        return fatecCourses.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.clickListener = onClickListener;
    }


    class CoursesViewHolder extends RecyclerView.ViewHolder {
        String courseName;

        private CourseItemBinding mBinding;

        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(courseName);
                }
            });
        }

        void bind(String courseName) {
            this.courseName = courseName;
        }


        public CourseItemBinding getmBinding() {
            return mBinding;
        }
    }


    interface OnItemClickListener {
        void onItemClick(String courseName);
    }
}
