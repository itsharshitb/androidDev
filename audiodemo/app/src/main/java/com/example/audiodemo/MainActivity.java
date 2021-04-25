package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {
//    boolean playing = false;
    MediaPlayer mediaplayer;
    public void play(View view)
    {
        mediaplayer.start();
//        playing = true;
    }
    public void pause(View view)
    {
        mediaplayer.pause();
        mediaplayer.
//        playing = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaplayer = MediaPlayer.create(this,R.raw.audio);
    }
}