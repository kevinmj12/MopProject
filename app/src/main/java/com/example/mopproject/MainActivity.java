package com.example.mopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomNavigationView);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.tab_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment).commit();
                        return true;
                    case R.id.tab_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.login, profileFragment).commit();
                }
                return false;
            }
        });

        Button btnJoinmembership = (Button) findViewById(R.id.btnJoinmembership);
        btnJoinmembership.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), JoinmembershipActivity.class);
                startActivity(intent);
            }
        });
    }

}