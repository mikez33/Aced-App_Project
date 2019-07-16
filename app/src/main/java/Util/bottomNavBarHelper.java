package Util;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.andre.aced.Checklist;
import com.example.andre.aced.Calendar;
import com.example.andre.aced.Notes;
import com.example.andre.aced.Option;
import com.example.andre.aced.R;


public class bottomNavBarHelper {

    public static void enbaleNav(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_calendar:
                        Intent intent1 = new Intent(context, Calendar.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.navigation_checklist:
                        Intent intent2 = new Intent(context, Checklist.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.navigation_notes:
                        Intent intent3 = new Intent(context, Notes.class);
                        context.startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }
}
