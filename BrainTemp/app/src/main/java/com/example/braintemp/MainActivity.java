package com.example.braintemp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Declare globally
    TextView ques;
    TextView timer;
    TextView score;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button intro;
    Button playagain;
    Random rand;
    int a,b;
    boolean flag=true;
    ArrayList<Integer> arr = new ArrayList<Integer>();
    int answerPosition;
    TextView comment;
    int cans,total;
    ConstraintLayout gamelayout;
    //onclicks and others


    public void playagain(View view)
    {
        flag=true;
        playagain.setVisibility(View.INVISIBLE);
        total=0;
        cans=0;
        score.setText(cans+"/"+total);
        timer.setText("10s");
        newtimer( findViewById(R.id.textView));
        comment.setText("");
    }


    public void inv(View view)
    {
        intro.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        update();
        newtimer( findViewById(R.id.textView));
    }
    public void answer(View view)
    {
      if(flag)
      {
          String ans = view.getTag().toString();
          if(ans.equals(Integer.toString(answerPosition)))
          {
              cans++;
              total++;
              comment.setText("Correct !!");
          }
          else
          {
              total++;
              comment.setText("Wrong !!");
          }
          score.setText(cans+"/"+total);
          update();
      }
    }

    //update
    void update()
    {
        //main logic
        if(flag)
        {
            arr.clear();
            rand = new Random();
            a = rand.nextInt(21);
            b = rand.nextInt(21);
            ques.setText(a + " + " + b);
            answerPosition = rand.nextInt(4);
            for(int i=0;i<4;i++)
            {
                if(i==answerPosition)
                {
                    arr.add(a+b);
                }
                else
                {
                    int temp = rand.nextInt(41);
                    while(temp==(a+b))
                    {
                        temp = rand.nextInt(41);
                    }
                    arr.add(temp);
                }
            }
            b0.setText(Integer.toString(arr.get(0)));
            b1.setText(Integer.toString(arr.get(1)));
            b2.setText(Integer.toString(arr.get(2)));
            b3.setText(Integer.toString(arr.get(3)));
        }
    }

    public void newtimer(View view)
    {
        //timer
        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                comment.setText("Game Over !!");
                flag=false;
                playagain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetching with ids
        intro = findViewById(R.id.intro);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        comment = findViewById(R.id.comment);
        score = findViewById(R.id.textView2);
        timer = findViewById(R.id.textView);
        playagain = findViewById(R.id.playagain);
        ques = findViewById(R.id.textView3);
        gamelayout = findViewById(R.id.gamelayout);

        //1st screen
        intro.setVisibility(View.VISIBLE);
        gamelayout.setVisibility(View.INVISIBLE);
    }
}