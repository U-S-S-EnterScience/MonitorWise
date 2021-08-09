package br.com.monitorwise.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import br.com.monitorwise.R;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tmp = findViewById(R.id.tmp);

        tmp.setAdapter();

    }
}