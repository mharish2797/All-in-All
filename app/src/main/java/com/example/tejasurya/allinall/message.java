package com.example.tejasurya.allinall;

/**
 * Created by TejaSurya on 25-03-2016.
 */
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

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.net.URI;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class message extends ActionBarActivity implements View.OnClickListener {
    Button btn, btnService;
    EditText editSMS, editMob;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        btnService = (Button) findViewById(R.id.btnservice);
        btnService.setOnClickListener(this);
        editSMS = (EditText) findViewById(R.id.editTextSMS);
        editMob = (EditText) findViewById(R.id.editTextMob);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        final int i = view.getId();
        final String phoneNo = editMob.getText().toString();
        final String msg = editSMS.getText().toString();
        switch (i) {
            case R.id.button:
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.btnservice: {
                final Dialog dialog=new Dialog(this);
                dialog.setContentView(R.layout.cal);
                final DatePicker datePicker=(DatePicker)dialog.findViewById(R.id.datePicker);
                final TimePicker timePicker=(TimePicker)dialog.findViewById(R.id.timePicker);
                final Button btnSetSMS=(Button)dialog.findViewById(R.id.buttonSetSMS);
                dialog.show();
                btnSetSMS.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                        calendar.set(Calendar.MONTH,datePicker.getMonth());
                        calendar.set(Calendar.YEAR,datePicker.getYear());
                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                        calendar.set(Calendar.SECOND, 0);
                        Intent intent = new Intent(message.this.getApplicationContext(), MyReceiver.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("sms",editSMS.getText().toString());
                        bundle.putString("mob",editMob.getText().toString());
                        intent.putExtra("bundle",bundle);

                        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),
                                12345, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        dialog.dismiss();
                    }
                });
            }

        }
    }

}