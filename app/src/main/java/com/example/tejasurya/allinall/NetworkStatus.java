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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by TejaSurya on 31-03-2016.
 */
public class NetworkStatus {
    public static int getNetworkStatus(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            switch (networkInfo.getType()){
                case ConnectivityManager.TYPE_MOBILE:
                    return 1;
                case ConnectivityManager.TYPE_WIFI:
                    return 2;
                default:
                    return  0;
            }
        }else {
            return 0;
        }
    }}
