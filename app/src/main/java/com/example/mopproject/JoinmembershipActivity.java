package com.example.mopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class JoinmembershipActivity extends AppCompatActivity {
    EditText jmId, jmPw, jmCheckPw, jmName, jmBirthday, jmPhone, jmAddress;
    TextView textviewFineId, textviewFinePw, textviewCheckPw;
    Button btnJoin, btnJmFineId;
    CheckBox checkBox;
    SharedPreferences jmSpref;
    SharedPreferences.Editor jmEditor;
    int membersCount = 0;
    boolean idFine = false, pressIdBtn = false, pwFine = false, checkPwFine = false;
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

    public static boolean isFinePw(String pw){
        boolean inEng = false, inNum = false, inSpecialLetter = false;
        if (pw.length() < 6){
            return false;
        }
        for (int i = 0; i < pw.length(); i++){
            if ('a' <= pw.charAt(i) & pw.charAt(i) <= 'z'){
                inEng = true;
            }
            else if ('0' <= pw.charAt(i) & pw.charAt(i) <= '9'){
                inNum = true;
            }
            else{
                inSpecialLetter = true;
            }
        }
        if (inEng & inNum & inSpecialLetter){
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinmembership);

//        editText
        jmId = (EditText) findViewById(R.id.jmId);
        jmPw = (EditText) findViewById(R.id.jmPw);
        jmCheckPw = (EditText) findViewById(R.id.jmCheckPw);
        jmName = (EditText) findViewById(R.id.jmName);
        jmBirthday = (EditText) findViewById(R.id.jmBirthday);
        jmPhone = (EditText) findViewById(R.id.jmPhone);
        jmAddress = (EditText) findViewById(R.id.jmAddress);
//        textview
        textviewFineId = (TextView) findViewById(R.id.textviewFineId);
        textviewFinePw = (TextView) findViewById(R.id.textviewFinePw);
        textviewCheckPw = (TextView) findViewById(R.id.textviewCheckPw);
//        button
        btnJmFineId = (Button) findViewById(R.id.btnJmFineId);
        btnJoin = (Button) findViewById(R.id.btnJoin);
//        checkbox
        checkBox =(CheckBox) findViewById(R.id.checkBox);
//        preference, membersCount
        while (true){
            jmSpref = getSharedPreferences("joinmembership"+Integer.toString(membersCount), Activity.MODE_PRIVATE);
            jmEditor = jmSpref.edit();
            if (jmSpref.getString("id", "x").equals("x"))
                break;
            membersCount++;
        }

//        check id is satisfied
        btnJmFineId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String strJmId = jmId.getText().toString();
                boolean isOverlapId = false;
                pressIdBtn = true;

                for (int i = 0; i < membersCount; i++){
                    SharedPreferences checkOverlapSpref = getSharedPreferences("joinmembership"+Integer.toString(i), Activity.MODE_PRIVATE);
                    if (strJmId.equals(checkOverlapSpref.getString("id", ""))){
                        isOverlapId = true;
                    }
                }
                idFine = false;
                if (isOverlapId) {
                    textviewFineId.setText("중복된 아이디입니다");
                    textviewFineId.setTextColor(0xFFFF0000);
                }
                else if (!isFineId(strJmId)){
                    textviewFineId.setText("6자 이상의 영문, 숫자를 입력해주세요");
                    textviewFineId.setTextColor(0xFFFF0000);
                }
                else{
                    textviewFineId.setText("사용 가능한 아이디입니다");
                    textviewFineId.setTextColor(0xFF0000FF);
                    idFine = true;
                }
            }
        });
        TextView testtest = (TextView) findViewById(R.id.testtest);
        testtest.setText(Integer.toString(membersCount));

//        check pw is satisfied
        jmPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isFinePw(jmPw.getText().toString())){
                    textviewFinePw.setText("사용 가능한 비밀번호입니다");
                    textviewFinePw.setTextColor(0xFF0000FF);
                    pwFine = true;
                }
                else{
                    textviewFinePw.setText("6자 이상의 영문, 숫자, 특수문자를 입력해주세요");
                    textviewFinePw.setTextColor(0xFFFF0000);
                    pwFine = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        check checkPw is satisfied
        jmCheckPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (jmPw.getText().toString().equals(jmCheckPw.getText().toString())){
                    textviewCheckPw.setText("비밀번호가 일치합니다");
                    textviewCheckPw.setTextColor(0xFF0000FF);
                    checkPwFine = true;
                }
                else{
                    textviewCheckPw.setText("비밀번호가 일치하지 않습니다");
                    textviewCheckPw.setTextColor(0xFFFF0000);
                    checkPwFine = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


//        join membership
        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                jmSpref = getSharedPreferences("joinmembership"+Integer.toString(membersCount), Activity.MODE_PRIVATE);
                jmEditor = jmSpref.edit();

                String strJmId = jmId.getText().toString();
                String strJmPw = jmPw.getText().toString();
                String strName = jmName.getText().toString();
                String strBirthday = jmBirthday.getText().toString();
                String strPhone = jmPhone.getText().toString();
                String strAddress = jmAddress.getText().toString();

                if (!pressIdBtn){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("아이디 중복 확인을 해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!idFine){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("올바른 아이디를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!pwFine){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("올바른 비밀번호를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!checkPwFine){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("비밀번호가 일치하지 않습니다");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (strName.length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("이름을 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (strBirthday.length()!=8){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("생년월일 8자리를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (strPhone.length()!=11){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("전화번호 11자리를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (strAddress.length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("주소를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (!checkBox.isChecked()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("개인정보 사용 약관에 동의해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    jmEditor.putString("id", strJmId);
                    jmEditor.putString("pw", strJmPw);
                    jmEditor.putString("name", strName);
                    jmEditor.putString("birthday", strBirthday);
                    jmEditor.putString("phone", strPhone);
                    jmEditor.putString("address", strAddress);
                    jmEditor.commit();
                    membersCount++;
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinmembershipActivity.this);
                    builder.setMessage("회원가입이 완료되었습니다!");
                    builder.setPositiveButton("로그인하러가기", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }

}