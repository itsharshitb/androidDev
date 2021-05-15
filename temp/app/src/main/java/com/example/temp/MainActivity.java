package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    Button btn;
    TextView timer;
    boolean isrunning=false;
    CountDownTimer countDownTimer;


    public void clicked(View view)
    {
        if(isrunning)
        {
            reset();
        }
        else {
            isrunning=true;
            seekBar.setEnabled(false);
            btn.setText("Stop!");
            Log.i("info", "button tapped!");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    Log.i("info", "ended");
                    MediaPlayer media = new MediaPlayer().create(getApplicationContext(), R.raw.airhorn);
                    media.start();
                    reset();
                }
            }.start();
        }
    }


    public void reset(){
        btn.setText("GO!");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        isrunning=false;
        timer.setText("0:30");
        countDownTimer.cancel();
    }

    public void update(int min)
    {
        int minute = min/60;
        int second = min - (minute*60);
        String dispMin = Integer.toString(minute);
        String dispSec = Integer.toString(second);
        if(dispMin.equals("0"))
            dispMin="00";
        if(second<10)
            dispSec="0"+second;
        timer.setText(dispMin+":"+dispSec);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        btn = findViewById(R.id.button);
        timer = findViewById(R.id.countdown);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}