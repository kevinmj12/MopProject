package com.example.mopproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.security.MessageDigest;

public class MainLogin extends AppCompatActivity{
    SharedPreferences saveSpref, membersSpref;
    SharedPreferences.Editor saveEditor, membersEditor;
    Button btnLogin, btnJoinmembership, btnGoHome;
    EditText loginId, loginPw;
    boolean successLogin = false;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnJoinmembership = (Button) findViewById(R.id.btnJoinmembership);
        btnGoHome = (Button) findViewById(R.id.btnGoHome);
//        setting preference, membersCount
        saveSpref = getSharedPreferences("saveId", MODE_PRIVATE);
        saveEditor = saveSpref.edit();

        btnJoinmembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinmembershipActivity.class);
                startActivity(intent);
            }
        });

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainLogin.this, MainActivity.class);
                intent.putExtra("isStart", false);
                startActivity(intent);
                finish();
            }
        });


        loginId = (EditText) findViewById(R.id.loginId);
        loginPw = (EditText) findViewById(R.id.loginPassword);


        String temp1 = saveSpref.getString("loginId", "");
        String temp2 = saveSpref.getString("loginPw", "");
        loginId.setText(temp1);
        loginPw.setText(temp2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataId = loginId.getText().toString();
                String dataPw = loginPw.getText().toString();
                saveEditor.putString("loginId", dataId);
                saveEditor.putString("loginPw", dataPw);
                saveEditor.commit();

                int cnt = -1;
                while (true){
                    cnt++;
                    membersSpref = getSharedPreferences("joinmembership"+Integer.toString(cnt), Activity.MODE_PRIVATE);
                    membersEditor = membersSpref.edit();
                    if (membersSpref.getString("id", "x").equals("x")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainLogin.this);
                        builder.setMessage("????????? ?????? ??????????????? ???????????? ????????????");
                        builder.setPositiveButton("??????", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    }

                    else if (membersSpref.getString("id", "").equals(dataId)){
                        if (membersSpref.getString("pw", "").equals(dataPw)) {
                            successLogin = true;
                            Toast.makeText(getApplicationContext(), "????????? ???????????????", Toast.LENGTH_SHORT).show();
//                            mainLogin??? ????????? ?????? ????????? ?????? ?????? ??????
                            Intent intent = new Intent(MainLogin.this, MainActivity.class);
                            intent.putExtra("successLogin", successLogin);
                            intent.putExtra("userNumber", cnt);



                            startActivity(intent);
                            finish();
                            break;
                        }
                    }
                }
            }
        });
    }

}