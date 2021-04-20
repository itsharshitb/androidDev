package com.example.bartandhomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean bartisactive = true; //initially bart will be seen in the screen
    public void click(View view)
    {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        if(bartisactive)
        {
            Log.i("info", "Bart tapped");   //response will be seen in terminal while tapping in image
            imageView.animate().alpha(0).setDuration(2000); //alpha = 0 means invisible... 2000 is in millisec..ok
            //initially homer is invisible..
            imageView2.animate().alpha(1).setDuration(2000);    //while bart will fade-out at same time homar will fade-in
            //setting bartisactibe to false as bart will be invisible by above operactions
            bartisactive = false;
        }
        else {
            Log.i("info", "Homer tapped");
            imageView2.animate().alpha(0).setDuration(2000); // while tapping on homer , homer will fade out and bart will start comming to display
            imageView.animate().alpha(1).setDuration(2000);
            bartisactive = true;    //as homer disaappear bart will be on screen
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
//oops lets debug it
//thanks for watching..
