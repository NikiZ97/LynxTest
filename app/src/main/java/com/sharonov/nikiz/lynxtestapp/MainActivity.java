package com.sharonov.nikiz.lynxtestapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        toolbar.setTitle("Football");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_football:
                            toolbar.setTitle("Football");
                            return true;
                        case R.id.navigation_basketball:
                            toolbar.setTitle("Basketball");
                            return true;
                        case R.id.navigation_hockey:
                            toolbar.setTitle("Hockey");
                            return true;
                        case R.id.navigation_tennis:
                            toolbar.setTitle("Tennis");
                            return true;
                        case R.id.navigation_volleyball:
                            toolbar.setTitle("Voleyball");
                            return true;
                    }
                    return false;
                }
            };
}
