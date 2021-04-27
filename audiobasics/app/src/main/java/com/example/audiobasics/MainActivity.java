package com.example.audiobasics;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer media;
    AudioManager audioManager;
    public void play(View view)
    {
        media.start();
    }
    public void pause(View view)
    {
        media.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        media = MediaPlayer.create(this,R.raw.audio);
        SeekBar volumeControl = (SeekBar) findViewById(R.id.seekBar);
        int maxvol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curvol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeControl.setMax(maxvol);
        volumeControl.setProgress(curvol);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar songcon = (SeekBar) findViewById(R.id.seekBar2);
        songcon.setMax(media.getDuration());
        songcon.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                media.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                songcon.setProgress(media.getCurrentPosition());
            }
        }, 0,3000);
    }
}