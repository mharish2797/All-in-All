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
        any later version.
        All in All is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY;
*/

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class se extends Activity{
    private WebView wv1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.se);
        int status=NetworkStatus.getNetworkStatus(this);
        if(status==0)
        {
            Toast.makeText(this,"Please connect to Internet for better Interface...",Toast.LENGTH_LONG).show();
        }
        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        String url ="https://www.google.co.in/";
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);
    }
    private class MyBrowser extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}