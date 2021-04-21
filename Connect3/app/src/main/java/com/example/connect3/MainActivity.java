package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //0: Yellow , 1: Red, 2: empty
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer = 0;
    boolean gameactive = true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2 && gameactive)
        {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if(activeplayer==0)
            {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for(int[] winningposition: winningpositions)
            {
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]] && gamestate[winningposition[1]]==gamestate[winningposition[2]] && gamestate[winningposition[0]]!=2)
                {
                    String winner="";
                    gameactive = false;
                    if(activeplayer == 1)
                    {
                        winner = "Yellow ";
                    }
                    else
                        winner = "Red";
//                   Toast.makeText(this, winner +" has won !", Toast.LENGTH_SHORT).show();
                   TextView winners = (TextView) findViewById(R.id.winners);
                   winners.setText(winner +" has won !");
                   winners.setVisibility(View.VISIBLE);
                   Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
                   playagainbutton.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playagain(View view)
    {
        TextView winners = (TextView) findViewById(R.id.winners);
        winners.setVisibility(View.INVISIBLE);
        Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
        playagainbutton.setVisibility(View.INVISIBLE);

        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridlayout);
        for(int i=0;i<gridlayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) gridlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
//        Toast.makeText(this, "Button tapped", Toast.LENGTH_SHORT).show();
        for(int i=0;i<gamestate.length;i++)
            gamestate[i]=2;
        activeplayer = 0;
        gameactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}










