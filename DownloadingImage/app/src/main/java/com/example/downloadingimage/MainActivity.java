package com.example.downloadingimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity<bitmap> extends AppCompatActivity {

    ImageView img;

    public void download(View view){
        downloadimage down = new downloadimage();
        Bitmap btmp;
        try {
            btmp = down.execute("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.tPmqg_u7LvFF4oovzEgPmAHaDt%26pid%3DApi&f=1").get();
            img.setImageBitmap(btmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
    }

    class downloadimage extends AsyncTask<String, Void , Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(in);
                Log.i("img", String.valueOf(mybitmap));
                return mybitmap;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}