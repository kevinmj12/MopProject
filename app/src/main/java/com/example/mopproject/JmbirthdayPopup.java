package com.example.mopproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.view.WindowManager;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.GregorianCalendar;

public class JmbirthdayPopup extends AppCompatActivity {

    private int mYear=0, mMonth=0, mDay=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.popup_jmbirthday);

        Calendar calender = new GregorianCalendar();
        mYear = calender.get(Calendar.YEAR);
        mMonth = calender.get(Calendar.MONTH);
        mDay = calender.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = findViewById(R.id.vDatePicker);
        datePicker.init(mYear,mMonth,mDay,mOnDateChangedListener);
    }

    public void mOnClick(View v){
        Intent intent = new Intent();
        intent.putExtra("mYear", mYear);
        intent.putExtra("mMonth", mMonth);
        intent.putExtra("mDay", mDay);
        setResult(RESULT_OK, intent);
        finish();
    }

    DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener(){
        public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd){
            mYear = yy;
            mMonth = mm;
            mDay = dd;
        }
    };
}