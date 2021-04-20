package com.example.animationinbart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public void click(View view) {
        ImageView bart = (ImageView) findViewById(R.id.bart);
//        bart.animate().translationX(1000).setDuration(2000); //unit is dp...lets run duration unt is in ms
//        bart.animate().translationXBy(1000).setDuration(2000); //this is about how much unit fromX
//        bart.animate().translationY(-1000).setDuration(2000); // this is in Y-axis... i.e vertically
//        bart.animate().rotation(-1000).setDuration(2000); //clockwise and anti-clockwise
//        bart.animate().scaleY(0.5f).scaleX(0.5f).setDuration(2000);     //scale.. i.e increase or decrease
        //moreover ve can perform combination of these all animations together... look
        bart.animate().rotation(3600).translationX(100).alpha(1).scaleX(0.78f).scaleY(0.78f).setDuration(2000); //bart is invisible due to alpha(0),
        //with alpha(1) we are bringing in bart lets see how
//        can you see this??
//        its amazing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bart = (ImageView) findViewById(R.id.bart);
        bart.animate().alpha(0);    //this is the block which run very first while rendering so bart will be invisible initially
    }
}