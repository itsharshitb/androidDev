package com.example.weatherapp;
//api key: 4a91b112d83a2c5fdb11d3347cd4dd9f
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText city;
    TextView results;
    public void getWeather(View view)
    {
        try {
            DownloadTask task = new DownloadTask();
            String encodedCityName = URLEncoder.encode(city.getText().toString(), "UTF-8");
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=4a91b112d83a2c5fdb11d3347cd4dd9f");
            InputMethodManager mgn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgn.hideSoftInputFromWindow(city.getWindowToken(), 0);
        } catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
        }
    }


    public class DownloadTask extends AsyncTask<String,Void,String>
    {

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... urls) {

            String result="";
            URL url;
            HttpURLConnection httpURLConnection=null;
            try{
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while(data!=-1)
                {
                    char ch = (char)data;
                    result+=ch;
                    data = reader.read();
                }
//                Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                return result;
            } catch(Exception e) {
                Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                Log.i("Error breaker","exception while fetching json");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                JSONArray jsonArray = new JSONArray(weatherInfo);
                String message="";
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonPart = jsonArray.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");
//                    Log.i("main",jsonPart.getString("main"));
//                    Log.i("description",jsonPart.getString("description"));
                    if(!main.equals("") && !description.equals(""))
                    {
                        message+=main+" : " + description+"\r\n";
                    }
                }
                if(!results.equals(""))
                {
                    results.setText(message);
                }
                else
                {
                    Log.i("Error breaker","result is empty");
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Log.i("Error breaker","invalid city");
                Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = findViewById(R.id.cityname);
        results = findViewById(R.id.ResultScreen);
    }
}