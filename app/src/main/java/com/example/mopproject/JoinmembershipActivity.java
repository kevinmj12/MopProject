package com.example.mopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class JoinmembershipActivity extends AppCompatActivity {
    EditText jmId, jmPw, jmCheckPw, jmName, jmBirthday, jmPhone;
    TextView jmFineId;
    Button btnJoin, btnJmFineId;
    SharedPreferences jmSpref;
    SharedPreferences.Editor jmEditor;
    int membersCount = 0;

    public static boolean isFineId(String id){
        boolean inEng = false, inNum = false;
        if (id.length() < 6){
            return false;
        }
        for (int i = 0; i < id.length(); i++){
            if ('a' <= id.charAt(i) & id.charAt(i) <= 'z'){
                inEng = true;
            }
            else if ('0' <= id.charAt(i) & id.charAt(i) <= '9'){
                inNum = true;
            }
        }
        if (inEng & inNum){
            return true;
        }
        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinmembership);

        jmSpref = getSharedPreferences("joinmembership"+Integer.toString(membersCount), Activity.MODE_PRIVATE);
        jmEditor = jmSpref.edit();

//        editText
        jmId = (EditText) findViewById(R.id.jmId);
        jmPw = (EditText) findViewById(R.id.jmPw);
        jmCheckPw = (EditText) findViewById(R.id.jmCheckPw);
        jmName = (EditText) findViewById(R.id.jmName);
        jmBirthday = (EditText) findViewById(R.id.jmBirthday);
        jmPhone = (EditText) findViewById(R.id.jmPhone);
//        textview
        jmFineId = (TextView) findViewById(R.id.jmFineId);
//        button
        btnJmFineId = (Button) findViewById(R.id.btnJmFineId);
        btnJoin = (Button) findViewById(R.id.btnJoin);

        btnJmFineId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String strJmId = jmId.getText().toString();

                if (isFineId(strJmId)){
                    jmFineId.setText("사용 가능한 아이디입니다");
                    jmFineId.setTextColor(0xFF0000FF);
                }
                else{
                    jmFineId.setText("6자 이상의 영문, 숫자를 입력해주세요");
                    jmFineId.setTextColor(0xFFFF0000);
                }
            }
        });
        jmCheckPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ()
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean idFine = false, pwFine = false, nameFine = false,
                        birthdayFine = false, phoneFine = false;




//                jmEditor.putString("id", strJmId);
//                jmEditor.putString("pw", strJmPw);
            }
        });

//        jmId.setText(spref.getString())

    }


}