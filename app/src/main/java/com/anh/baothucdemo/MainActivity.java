package com.anh.baothucdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button bthengio, btstop;
    CheckBox cn, t2, t3, t4, t5, t6, t7;
    TextView txthienthi;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        cn = findViewById(R.id.cn);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);

        bthengio = (Button)findViewById(R.id.bthengio);
        btstop = (Button)findViewById(R.id.btstop);
        txthienthi = (TextView)findViewById(R.id.textView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this,AlamReceiver.class);

        bthengio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                int gio =timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();
                String string_gio = String.valueOf(gio);
                String string_phut = String.valueOf(phut);


                if (gio > 12){
                    string_gio = String.valueOf(gio - 12);
                }
                if (phut < 10){
                    string_phut = 0 + String.valueOf(phut);
                }
                intent.putExtra("extra","on");
                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                txthienthi.setText("Giờ bạn đặt là:"+ string_gio + ":" + string_phut);
            }
        });

        btstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txthienthi.setText("Dừng lại");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);
            }
        });
    }
}