package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    ListView table;
    public void generate(int timestablenumber)
    {
        ArrayList<String> content = new ArrayList<String>();
        for (int i=1;i<=10;i++)
        {
            content.add(Integer.toString(i*timestablenumber));
        }
        ArrayAdapter<String> str = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,content);
        table.setAdapter(str);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        table = findViewById(R.id.table);
        int max = 20;
        int startingposition = 10;
        seekBar.setMax(max); //limit of numbers
        seekBar.setProgress(startingposition);
        generate(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min=1;
                int timestablenumber;
                if(progress<min)
                {
                    timestablenumber = min;
                    seekBar.setProgress(min);
                }
                else {
                    timestablenumber = progress;
                }
                Log.i("info", Integer.toString(timestablenumber));
                generate(timestablenumber);
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