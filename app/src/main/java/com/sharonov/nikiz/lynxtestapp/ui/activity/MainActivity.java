package com.sharonov.nikiz.lynxtestapp.ui.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.sharonov.nikiz.lynxtestapp.R;
import com.sharonov.nikiz.lynxtestapp.ui.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNetworkAvailable()) {
            Toast.makeText(this, R.string.check_internet_connection, Toast.LENGTH_LONG).show();
            return;
        }

        toolbar = getSupportActionBar();
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        toolbar.setTitle(R.string.title_football);
        loadFragmentWithCategory(getString(R.string.retrofit_football));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_football:
                            toolbar.setTitle(R.string.title_football);
                            loadFragmentWithCategory(getString(R.string.retrofit_football));
                            return true;
                        case R.id.navigation_basketball:
                            toolbar.setTitle(R.string.title_basketball);
                            loadFragmentWithCategory(getString(R.string.retrofit_basketball));
                            return true;
                        case R.id.navigation_hockey:
                            toolbar.setTitle(R.string.title_hockey);
                            loadFragmentWithCategory(getString(R.string.retrofit_hockey));
                            return true;
                        case R.id.navigation_tennis:
                            toolbar.setTitle(R.string.title_tennis);
                            loadFragmentWithCategory(getString(R.string.retrofit_tennis));
                            return true;
                        case R.id.navigation_volleyball:
                            toolbar.setTitle(R.string.title_volleyball);
                            loadFragmentWithCategory(getString(R.string.retrofit_volleyball));
                            return true;
                    }
                    return false;
                }
            };

    private void loadFragmentWithCategory(String category) {
        Fragment fragment;
        fragment = NewsFragment.newInstance(category);
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
