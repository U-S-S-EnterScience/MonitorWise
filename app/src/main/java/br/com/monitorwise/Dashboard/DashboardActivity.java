package br.com.monitorwise.Dashboard;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.monitorwise.R;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        RecyclerView rvCalendar = findViewById(R.id.recycler_view_calendar);

        // Comportamento de exibição da recycler view
        rvCalendar.setLayoutManager(new LinearLayoutManager(this));

        CalendarAdapter calendarAdapter = new CalendarAdapter();    // Instancia o adaptador
        rvCalendar.setAdapter(calendarAdapter);                     // Define o adaptador


        bottomNavigationView.setOnItemSelectedListener(item -> {

           switch(item.getItemId()){
               case R.id.calendarFragment:
                   Toast.makeText(this, "Exibe o calendario", Toast.LENGTH_SHORT).show();
                   break;

               case R.id.monitorFragment:
                   Toast.makeText(this, "Exibe as informações do monitor", Toast.LENGTH_SHORT).show();
                   break;

               case R.id.historicFragment:
                   Toast.makeText(this, "Exibe o histórico", Toast.LENGTH_SHORT).show();
           }
            return true;
        });

    }


    private class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

        @NonNull
        @Override
        public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CalendarViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DashboardActivity.CalendarViewHolder holder, int position) {

        }

        // Quantidade de células na recycler view
        @Override
        public int getItemCount() {
            return 20;
        }
    }

    // View da celula que esta dentro da recycler view
    private class CalendarViewHolder extends RecyclerView.ViewHolder{

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



}