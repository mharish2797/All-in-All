package com.example.tejasurya.allinall;
/*
Copyright  Teja Surya
        This file is part of All in All.

        All in All is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        All in All is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY;
        */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.        Bundle bundle=intent.getBundleExtra("bundle");
        Bundle bundle=intent.getBundleExtra("bundle");
        String sms=bundle.getString("sms");
        String mob=bundle.getString("mob");
        sendSMS(sms,mob);
        generateNotify(context,sms,mob);

        throw new UnsupportedOperationException("Not yet implemented");
    }
    private void generateNotify(Context context, String sms, String mob) {
        NotificationCompat.Builder mBuild = new NotificationCompat.Builder(context);
        mBuild.setTicker("SMS Service");
        mBuild.setContentText("Content Text Sent:" + sms);
        mBuild.setContentTitle("Content Title:sent to" + mob);
        mBuild.setAutoCancel(true);

        Intent resultIntent=new Intent(context,message.class);

        PendingIntent resultPendingIntent=PendingIntent.getActivity(context,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuild.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1111, mBuild.build());
    }
    Context context=null;
    private void sendSMS(String sms, String mob) {
        SmsManager.getDefault().sendTextMessage(mob, null, sms, null, null);
    }
}
