package com.example.mopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    ProfileFragment profileFragment;

    boolean isLogin = false;
    boolean isStart = true;
    int userNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment).commit();

        Intent getIntent = getIntent();
        isLogin = getIntent.getBooleanExtra("successLogin", false);
        isStart = getIntent.getBooleanExtra("isStart", true);
        userNumber = getIntent.getIntExtra("userNumber", -1);
        Bundle bundle = new Bundle();
        bundle.putInt("userNumber", userNumber);
        profileFragment.setArguments(bundle);

        Intent loginIntent = new Intent(getApplicationContext(), MainLogin.class);
        if (!isLogin & isStart){
            startActivity(loginIntent);
            finish();
        }

        NavigationBarView navigationBarView = findViewById(R.id.bottomNavigationView);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.tab_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment).commit();
                        return true;
                    case R.id.tab_profile:
                        if (isLogin){
                            getSupportFragmentManager().beginTransaction().replace(R.id.login, profileFragment).commit();
                            return true;
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("로그인이 필요합니다").setPositiveButton("로그인", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton){
                                    Intent intent = new Intent(getApplicationContext(), MainLogin.class);
                                    startActivity(intent);
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