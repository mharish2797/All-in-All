package com.example.tejasurya.allinall;
/*
Copyright  Teja Surya
        This file is part of All in All.


        All in All is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY;
        */
        import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        int status=NetworkStatus.getNetworkStatus(this);
        switch (status){
            case 1:
                Toast.makeText(this,"Mobile Internet Connected...",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"WiFi Connected...",Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(this,"Please connect to Internet for better Interface...",Toast.LENGTH_LONG).show();
                break;
        }
        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        String url ="http://www.google.co.in/";
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.setting) {
            startActivity(
                    new Intent(Settings.ACTION_SETTINGS));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent in = new Intent();
        call c=new call();
        switch (id) {
            case R.id.nav_call:
                in.setClass(this, call.class);
                startActivity(in);
                break;
            case R.id.nav_message:
                in.setClass(this, message.class);
                startActivity(in);
                break;
            case R.id.nav_mp3:
                in.setClass(this, mp3.class);
                startActivity(in);
                break;
            case R.id.nav_mp4:
                in.setClass(this, mp4.class);
                startActivity(in);
                break;
            case R.id.nav_se:
                in.setClass(this, se.class);
                startActivity(in);
                break;
            case R.id.nav_map:
                in.setClass(this, map.class);
                startActivity(in);
                break;
            case R.id.file:
                in.setClass(this, FileBrowser.class);
                startActivity(in);
                break;
            case R.id.camera:
            {
                startCapture();
                return true;

            }

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class MyBrowser extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    private void startCapture() {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = CreateImageFile();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }

            if(photoFile != null)
            {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                int CAMERA_CAPTURE=0;
                startActivityForResult(cameraIntent, CAMERA_CAPTURE);
            }
        }
    }

    private File CreateImageFile() throws IOException
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Image_" + timeStamp + "_";
        File storageDirectory = getExternalFilesDir("/DCIM/images");
        File image = File.createTempFile(imageFileName, "abc.jpg", storageDirectory);
        return image;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Done by 2014503550",Toast.LENGTH_LONG).show();
    }
}
