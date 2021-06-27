package com.example.weatherpractice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText cityname;
    TextView resultout;
    TextView feeling;
    String ApiKey="4a91b112d83a2c5fdb11d3347cd4dd9f";


    public void find (View view) //onclick
    {
        String city = cityname.getText().toString();
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.openweathermap.org/data/2.5/weather?q=london&appid=4a91b112d83a2c5fdb11d3347cd4dd9f", null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            Log.i("response", response.getString("weather"));
                            JSONArray arr =response.getJSONArray("weather");
                            for(int i=0;i<arr.length();i++)
                            {
                                JSONObject obj = arr.getJSONObject(i);
                                String main=obj.getString("main");
                                Log.i("info",main);
                                String desc=obj.getString("description");
                                Log.i("info",desc);
                                resultout.setText(main+":"+desc);
                            }
                        } catch (JSONException e) {
                            //  resultout.setText("Please select valid location");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET,
                "https://api.openweathermap.org/data/2.5/weather?q=london&appid=4a91b112d83a2c5fdb11d3347cd4dd9f", null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    JSONObject arr = response.getJSONObject("main");

                    String temp = arr.getString("feels_like");
                    String pres = arr.getString("pressure");
                    String humidity = arr.getString("humidity");
                    feeling.setText("Temperature: " + temp+"\r\n"+ "Pressure: "+pres+"\r\n"+"Humidity: "+humidity);

                } catch (JSONException e) {
                    //  resultout.setText("Please select valid location");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // resultout.setText("Please select valid location");
                Log.i("Exception","Inside 2nd api fetch");
            }
        });


        requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonObjectRequest1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityname = findViewById(R.id.cityname);
        resultout = findViewById(R.id.resultout);
        feeling = findViewById(R.id.feeling);
    }
}