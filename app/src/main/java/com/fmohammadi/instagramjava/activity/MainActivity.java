package com.fmohammadi.instagramjava.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.fmohammadi.instagramjava.R;
import com.fmohammadi.instagramjava.fragment.HomeFragment;
import com.fmohammadi.instagramjava.fragment.NotificationFragment;
import com.fmohammadi.instagramjava.fragment.ProfileFragment;
import com.fmohammadi.instagramjava.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.fmohammadi.instagramjava.R.id.nav_add;
import static com.fmohammadi.instagramjava.R.id.nav_home;
import static com.fmohammadi.instagramjava.R.id.nav_like;
import static com.fmohammadi.instagramjava.R.id.nav_profile;
import static com.fmohammadi.instagramjava.R.id.nav_search;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case nav_home:
                        selectorFragment = new HomeFragment();
                        break;
                    case nav_search:
                        selectorFragment = new SearchFragment();
                        break;
                    case nav_add:
                        selectorFragment = null;
                       startActivity(new Intent(MainActivity.this , PostActivity.class));
                        break;
                    case nav_like:
                        selectorFragment = new NotificationFragment();
                        break;
                    case nav_profile:
                        selectorFragment = new ProfileFragment();
                        break;
                }
                if (selectorFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectorFragment).commit();
                }
                return true;
            }
        });
        //default fragment when activity start
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
}