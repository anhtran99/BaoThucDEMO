package com.anh.baothucdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("in Receiver","hello");
        String chuoi_string =intent.getExtras().getString("extra");
        Log.e("truyen key",chuoi_string);

        Intent myIntent = new Intent(context,Music.class);
        myIntent.putExtra("extra", chuoi_string);
        context.startService(myIntent);
    }
}
