package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public void click(View view)
    {
        ImageView bart = (ImageView) findViewById(R.id.bart);
        ImageView homar = (ImageView) findViewById(R.id.homar);
//        bart.animate().rotation(360).setDuration(1000);
//        bart.animate().scaleX(0.5f).scaleY(0.5f).alpha(0).setDuration(1000);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bart = (ImageView) findViewById(R.id.bart);
        bart.setX(-1000);
        bart.animate().translationXBy(1000).rotation(3600).setDuration(2000);
    }
}