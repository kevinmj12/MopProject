package com.example.mopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.content.DialogInterface;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    LoginFragment loginFragment;
    ProfileFragment profileFragment;
    boolean isLogin = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        loginFragment = new LoginFragment();
        profileFragment = new ProfileFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.login, loginFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomNavigationView);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.tab_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment).commit();
                        return true;
                    case R.id.tab_login:
                        getSupportFragmentManager().beginTransaction().replace(R.id.login, loginFragment).commit();
                        return true;
                    case R.id.tab_profile:
                        if (isLogin){
                            getSupportFragmentManager().beginTransaction().replace(R.id.login, loginFragment).commit();
                            return true;
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("로그인이 필요합니다").setPositiveButton("로그인", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){
                                    getSupportFragmentManager().beginTransaction().replace(R.id.login, loginFragment).commit();
                                }
                            }).setNegativeButton("회원가입", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){
                                    Intent intent = new Intent(getApplicationContext(), JoinmembershipActivity.class);
                                    startActivity(intent);
                                }
                            }).setNeutralButton("확인", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){
                                    dialog.cancel();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }
                }
                return false;
            }
        });



    }

}