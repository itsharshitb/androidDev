package com.example.temp2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;

    public void hide(View view)
    {
        text = findViewById(R.id.textView);
        text.setVisibility(View.INVISIBLE);
    }
    public void show(View view)
    {
        text = findViewById(R.id.textView);
        text.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}