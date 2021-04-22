package com.example.connect3practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0: yelow, 1: red, 2: empty
    int activeplayer=0;
    boolean gameactive=true;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};   //empty array
    int[][] winningposition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};    //all possible winning position
//    static public boolean flag(int[] gamestate)
//    {
//        for (int i=0;i<gamestate.length;i++)
//        {
//            if(gamestate[i]==2);
//            return false;
//        }
//        return true;
//    }
    int f=0;
    public void tapped(View view)
    {
        ImageView count = (ImageView) view;
        int tag = Integer.parseInt(count.getTag().toString());
        if(gamestate[tag]==2 && gameactive)
        {
            gamestate[tag] = activeplayer;
            count.setTranslationY(-1500);
            if(activeplayer == 1)
            {
                count.setImageResource(R.drawable.red);
                activeplayer=0;
            }
            else
            {
                count.setImageResource(R.drawable.yellow);
                activeplayer=1;
            }
            count.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for(int[] winningpositions: winningposition)
            {
                if(gamestate[winningpositions[0]] == gamestate[winningpositions[1]] && gamestate[winningpositions[1]]==gamestate[winningpositions[2]] && gamestate[winningpositions[1]]!=2) {
                    String str = "";
                    if (activeplayer == 1) {
                        str = "Yellow ";
                    } else
                        str = "RED ";
//                    Toast.makeText(this, , Toast.LENGTH_SHORT).show();
                    gameactive = false;
                    TextView winner = (TextView) findViewById(R.id.winnertextView2);
                    winner.setText(str + "has won");
                    winner.setVisibility(View.VISIBLE);
                    Button playagain = (Button) findViewById(R.id.playagainbutton2);
                    playagain.setVisibility(View.VISIBLE);
                }
            }
            for(int i=0;i<gamestate.length;i++)
            {
                if(gamestate[i]==2)
                {
                    f=1;
                }
            }
            if(f!=1)
            {
                String str="No one ";
                gameactive = false;
                TextView winner = (TextView) findViewById(R.id.winnertextView2);
                winner.setText(str + "has won");
                winner.setVisibility(View.VISIBLE);
                Button playagain = (Button) findViewById(R.id.playagainbutton2);
                playagain.setVisibility(View.VISIBLE);
            }
        }
    }
    public void again(View view)
    {
        TextView winner = (TextView) findViewById(R.id.winnertextView2);
        winner.setVisibility(View.INVISIBLE);
        Button playagain = (Button) findViewById(R.id.playagainbutton2);
        playagain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout2);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView count = (ImageView) gridLayout.getChildAt(i);
            count.setImageDrawable(null);   //hiding image
        }
        activeplayer=0;
        gameactive=true;
        for (int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}