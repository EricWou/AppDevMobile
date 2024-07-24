package com.example.courseproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class CourseService extends Service {
    //right click on package -> new -> Service -> Service

    //Create an object of MediaPlayer to play the music
    //MediaPlayer can also be used to play video (but will need to use implicit intent)
    MediaPlayer myPlayer;

    public CourseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //need to add these 3 methods
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();

        //right click on res -> new -> Directory -> name it raw

        //creates MediaPlayer object and links it to a file
        myPlayer = MediaPlayer.create(this, R.raw.halo);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();

        //starts the service
        myPlayer.start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();

        //stops the service
        myPlayer.stop();
    }
}