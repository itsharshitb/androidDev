package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class downloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls) {

            String result="";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                 url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while(data!=-1)
                {
                    char c = (char)data;
                    result+=c;
                    data = reader.read();
                }
                return result;
            } catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Log.i("JSON",s);
            try{
                JSONObject jsonObject = new JSONObject(s);
//                Log.i("JsonObj",jsonObject.toString());
                String weatherInfo = jsonObject.getString("weather");
//                Log.i("weather", weatherInfo);
                JSONArray arr = new JSONArray(weatherInfo);
//                Log.i("size", String.valueOf(arr.length()));
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    Log.i("main", jsonPart.getString("main"));
                    Log.i("description", jsonPart.getString("description"));
                }
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadTask task = new downloadTask();
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=London&appid=4a91b112d83a2c5fdb11d3347cd4dd9f");
    }
}