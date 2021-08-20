package br.com.monitorwise.Dashboard;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import br.com.monitorwise.Fragments.FirstFragment;
import br.com.monitorwise.Fragments.SecondFragment;
import br.com.monitorwise.Fragments.ThirdFragment;
import br.com.monitorwise.R;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_content, new FirstFragment());
        fragmentTransaction.commit();

        // Bottom Navigation Menu test
        bottomNavigationView.setOnItemSelectedListener(item -> {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

           switch(item.getItemId()){
               case R.id.calendarFragment:
                   ft.replace(R.id.fragment_content, new FirstFragment());
                   ft.commit();
                   break;

               case R.id.monitorFragment:
                   ft.replace(R.id.fragment_content, new SecondFragment());
                   ft.commit();
                   break;

               case R.id.historicFragment:
                   ft.replace(R.id.fragment_content, new ThirdFragment());
                   ft.commit();

           }
            return true;
        });

    }

}