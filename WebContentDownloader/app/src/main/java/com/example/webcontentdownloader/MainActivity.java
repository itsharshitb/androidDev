package com.example.webcontentdownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    public class downloader extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String result="";
            URL url;
            HttpURLConnection urlConnection=null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data!=-1)
                {
                    char ch = (char)data;
                    result+=ch;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return  "Failed !";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloader dn = new downloader();
        String str=null;
        try {
            str = dn.execute("https://www.google.com").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("return value",str);
    }
}