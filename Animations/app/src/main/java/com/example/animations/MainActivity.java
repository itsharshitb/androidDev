package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean bartisshowing = true;
    public void click(View view)
    {
        Log.i("info","tapped on homar");
        ImageView bart = (ImageView) findViewById(R.id.bart);
        ImageView homar = (ImageView) findViewById(R.id.homar);
        if(bartisshowing)
        {
            bart.animate().alpha(0).setDuration(2000);
            homar.animate().alpha(1).setDuration(2000);
            bartisshowing = false;
        }
        else
        {
            homar.animate().alpha(0).setDuration(2000);
            bart.animate().alpha(1).setDuration(2000);
            bartisshowing = true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}