package com.example.eggtimer;

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

    TextView timer;
    SeekBar seekbar;
    CountDownTimer countDownTimer;
    Button btn;
    boolean isrunning=false;

    public  void resetTimer()
    {
        countDownTimer.cancel();
        seekbar.setProgress(30);
        timer.setText("00 : 30");
        seekbar.setEnabled(true);
        btn.setText("GO");
        isrunning=false;
    }

    public void buttonPressed(View view){
        if(isrunning==false) {
            isrunning = true;
            timer.setEnabled(false);

            if (isrunning)
                btn.setText("STOP");

            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeft((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    Log.i("info", "finished");
                    MediaPlayer meadiaplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    meadiaplayer.start();
                    resetTimer();
                }
            }.start();
        }
        else
        {
            resetTimer();
        }
    }

    public void timeLeft(int time)
    {
        int minutes = time/60;
        int seconds = time - (minutes*60);
        String str = Integer.toString(seconds);
        String str1 = Integer.toString(minutes);
        if(seconds<10) {
            str = "0"+seconds;
        }
        if(str1.equals("0"))
            str1="00";

        timer.setText(str1+" : "+str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar  = findViewById(R.id.TimerseekBar);
        seekbar.setMax(600);
        seekbar.setProgress(30);
        timer = findViewById(R.id.CountdowntextView);
        btn = findViewById(R.id.Gobutton);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(isrunning==false)
                timeLeft(progress);
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