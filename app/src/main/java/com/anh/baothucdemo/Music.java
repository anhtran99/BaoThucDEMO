package com.anh.baothucdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.annotation.Nullable;

import android.provider.MediaStore;
import android.util.Log;
import java.security.Provider;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("in Music","hello");
        String nhankey = intent.getExtras().getString("extra");
        Log.e("music nhan key", nhankey);
        if (nhankey.equals("on")){
            id =1;
        }else if (nhankey.equals("off")){
            id = 0;
        }
        if(id == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.nhacchuong);
            mediaPlayer.start();
            CountDownTimer countDownTimer = new CountDownTimer(900000,300000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mediaPlayer.start();
                }

                @Override
                public void onFinish() {

                }
            };

        }else if(id == 0){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        return START_NOT_STICKY;
    }
}
