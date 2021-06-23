package com.example.weatherpractice;
// api key: 4a91b112d83a2c5fdb11d3347cd4dd9f
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText cityname;
    TextView resultout;
    String ApiKey="4a91b112d83a2c5fdb11d3347cd4dd9f";


    public void find (View view) //onclick
    {
        String city = cityname.getText().toString();
        DownloadTask task = new DownloadTask();
        try{
//            Log.i("value","https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+ApiKey);
                task.execute("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+ApiKey);
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(cityname.getWindowToken(),0);
        } catch(Exception e)
        {
            e.printStackTrace();
//            Toast.makeText(getApplicationContext(),"Sorry we can't get your request :(",Toast.LENGTH_SHORT).show();
            Log.i("location","inside OnClick");
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

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
//                Toast.makeText(getApplicationContext(),"Sorry we can't get your request :(",Toast.LENGTH_SHORT).show();
                Log.i("location","inside DownloadTask class");
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
                String ans="";
                JSONArray arr = new JSONArray(weatherInfo);
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String desc = jsonPart.getString("description");
                    if(!main.equals("") && !desc.equals(""))
                    {
                        ans += main + " : " + desc + "\r\n";
                    }
                    else
                    {
//                        Toast.makeText(getApplicationContext(),"Sorry we can't get your request :(",Toast.LENGTH_SHORT).show();
                        Log.i("location","inside else block");
                    }
                }
                resultout.setText(ans);
            } catch(Exception e)
            {
//                Toast.makeText(getApplicationContext(),"Sorry we can't get your request :(",Toast.LENGTH_SHORT).show();
                Log.i("location","inside OnPost method");
                resultout.setText("Make sure you have entered right country... -_-\"");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityname = findViewById(R.id.cityname);
        resultout = findViewById(R.id.resultout);
    }
}