package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainApp extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Bundle bundle1, bundle2;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bundle1 = getIntent().getExtras();
        bundle2 = new Bundle();
        bundle2.putString("email", bundle1.getString("email"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        bottomNavigationView.setSelectedItemId(R.id.navigation_create);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_create:
                            selectedFragment = new FragmentCreate();
                            selectedFragment.setArguments(bundle2);
                            break;
                        case R.id.navigation_update:
                            selectedFragment = new FragmentUpdate();
                            selectedFragment.setArguments(bundle2);
                            break;

                        case R.id.navigation_account:
                            selectedFragment = new FragmentAccount();
                            selectedFragment.setArguments(bundle2);
                            break;
                    }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                        return true;
                    }
            };
}
