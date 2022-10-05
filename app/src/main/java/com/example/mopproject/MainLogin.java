package com.example.mopproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        Button btnJoinmembership = (Button) findViewById(R.id.btnJoinmembership);
        btnJoinmembership.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), JoinmembershipActivity.class);
                startActivity(intent);
            }
        });


        Button btnGoHome = (Button) findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
//                HomeFragment homeFragment = new HomeFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment);
            }
        });

    }
}