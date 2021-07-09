package com.example.weathernow;
//Api key 4a91b112d83a2c5fdb11d3347cd4dd9f
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.PrivilegedExceptionAction;

public class MainActivity extends AppCompatActivity {

    // defining variable
    TextView location;
    TextView weather;
    TextView details;
    LocationManager locationManager;
    LocationListener locationListener;
    String apikey = "4a91b112d83a2c5fdb11d3347cd4dd9f";


    //location req accept/reject

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>1 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,locationListener);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //accessing with id
        location = findViewById(R.id.location);
        weather = findViewById(R.id.weather);
        details = findViewById(R.id.details);

        //location fetch
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String lon = String.valueOf(location.getLongitude());
                lon = lon.substring(0,6);
                String lat = String.valueOf(location.getLatitude());
                lat = lat.substring(0,6);
                fetchdata(lat,lon);
            }
        };
        if(Build.VERSION.SDK_INT<23) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
            }
        }
    }

    private void fetchdata(String latitude, String longitude) {
        Log.i("inside","fetch data");
        Log.i("latlon", latitude);
        Log.i("latlon", longitude);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apikey, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("inside", "try catch");
                            //current location
                            String currentlocation="";
                            currentlocation = response.getString("name");
                            location.setText(currentlocation);
                            Log.i("location", String.valueOf(location));

                            //weather
                            String main = "";
                            JSONArray jsonArray = response.getJSONArray("weather");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                main = obj.getString("main");
                            }
                            weather.setText(main);
                            Log.i("main", String.valueOf(weather));

                            //more details
                            JSONObject jsonObject1 = response.getJSONObject("main");
                            String temp = jsonObject1.getString("temp");
                            String pressure = jsonObject1.getString("pressure");
                            String humidity = jsonObject1.getString("humidity");
                            String sealevel = jsonObject1.getString("sea_level");
                            String groundlevel = jsonObject1.getString("grnd_level");
                            details.setText("Temperature: "+temp+"°F\n"+
                                    "Pressure: "+pressure+"\n"+
                                    "Humidity: "+humidity+"\n"+
                                    "Sea Level: "+sealevel+"\n"+
                                    "Ground Level: "+groundlevel);

                            Log.i("details", String.valueOf(details));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                weather.setText("Cant find location :(");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}