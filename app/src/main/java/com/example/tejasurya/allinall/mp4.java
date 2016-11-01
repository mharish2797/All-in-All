package com.example.tejasurya.allinall;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by TejaSurya on 03-04-2016.
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

public class mp4 extends Activity {
    VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp4);
        videoview=(VideoView)findViewById(R.id.videoView);
        MediaController mediacontroller=new MediaController(this);
        mediacontroller.setAnchorView(videoview);
        try{
        Uri video=Uri.parse("/storage/sdcard1/video/Thoongavanam (2015) DVDScr Tamil Full Movie Watch Online - www.TamilYogi.tv[via torchbrowser.com].mp4");
            Toast.makeText(getApplicationContext(), "Enjoy the videos!!!", Toast.LENGTH_SHORT).show();

            videoview.setVideoURI(video);
        videoview.setMediaController(mediacontroller);
              videoview.start();
    }catch (Exception e){e.printStackTrace();}
    }

}
