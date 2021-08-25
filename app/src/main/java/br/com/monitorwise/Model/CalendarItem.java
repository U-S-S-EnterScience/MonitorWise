package br.com.monitorwise.Model;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import br.com.monitorwise.R;

/**
 * agosto, 25 2021
 * author: M.Lucas.
 */

public class CalendarItem extends Item<ViewHolder> {

    private final String disciplineName;
    private final String disciplineDate;
    private final String disciplineHour;
    private final String disciplineLocal;


    public CalendarItem (String disciplineName, String disciplineDate, String disciplineHour, String disciplineLocal) {
        this.disciplineName = disciplineName;
        this.disciplineDate = disciplineDate;
        this.disciplineHour = disciplineHour;
        this.disciplineLocal = disciplineLocal;
    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {

        // Pega as referencias dos elementosdo main item com o findviewbyid do view holder
        TextView tvDisciplineName = viewHolder.itemView.findViewById(R.id.main_item_discipline_name);
        TextView tvDisciplineDate = viewHolder.itemView.findViewById(R.id.main_item_date);
        TextView tvDisciplineHour = viewHolder.itemView.findViewById(R.id.main_item_hour);
        TextView tvDisciplineLocal = viewHolder.itemView.findViewById(R.id.main_item_local);


        tvDisciplineName.setText(disciplineName);
        tvDisciplineDate.setText(disciplineDate);
        tvDisciplineHour.setText(disciplineHour);
        tvDisciplineLocal.setText(disciplineLocal);

    }

    @Override
    public int getLayout() {
        return R.layout.main_item;
    }
}
