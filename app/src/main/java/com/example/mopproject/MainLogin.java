package com.example.mopproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;

public class MainLogin extends AppCompatActivity{
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    Button btnLogin, btnJoinmembership, btnGoHome;
    EditText loginId, loginPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        btnJoinmembership = (Button) findViewById(R.id.btnJoinmembership);
        btnJoinmembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinmembershipActivity.class);
                startActivity(intent);
            }
        });

        btnGoHome = (Button) findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                HomeFragment homeFragment = new HomeFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.login, homeFragment);
            }
        });

        spref = getSharedPreferences("saveId", MODE_PRIVATE);
        editor = spref.edit();

        loginId = (EditText) findViewById(R.id.loginId);
        loginPw = (EditText) findViewById(R.id.loginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        String temp1 = spref.getString("loginId", "");
        String temp2 = spref.getString("loginPw", "");
        loginId.setText(temp1);
        loginPw.setText(temp2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataId = loginId.getText().toString();
                String dataPw = loginPw.getText().toString();
                editor.putString("loginId", dataId);
                editor.putString("loginPw", dataPw);
                editor.commit();
            }
        });
    }
//
//    @Override
//    public void onClick(View view){
//        String dataId = loginId.getText().toString();
//        String dataPw = loginPw.getText().toString();
//        if (view.getId() == R.id.btnLogin){
//            editor.putString("loginId", dataId);
//            editor.putString("loginPw", dataPw);
//            editor.commit();
//        }
//}
}