package com.example.myapplication;
// key d0ff992edba86e5873038ec8da1dd74fdeaf330b
// secret 3828a077c01aef993fae8033ac3b763c4b2cf4b3
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity<bitmap> extends AppCompatActivity {
    EditText username;
    TextView data;
    ImageView imageofuser;
    TextView errorText;

    public class downloadimg extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection httpURLConnection;
            try{
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(in);

                return mybitmap;
            } catch(Exception e)
            {
                imageofuser.setImageBitmap(null);
                errorText.setText("Can't even copy a username properly? :(");
                errorText.setVisibility(View.VISIBLE);
                data.setText("");
            }
            return null;
        }
    }


    public void find(View view){
        errorText.setVisibility(View.INVISIBLE);
        InputMethodManager mng = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mng.hideSoftInputFromWindow(view.getWindowToken(),0);

        String name = String.valueOf(username.getText());
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, " https://codeforces.com/api/user.info?handles="+name,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.i("response", response);
                try {
                    JSONArray arr = response.getJSONArray("result");
                    String fname="";
                    String lname="";
                    String titlePhoto = "";
                    String country="";
                    int rating;
                    int maxRating;
                    int friendOfCount;
                    String rank= "";
                    String maxRank= "";
                    for(int i=0;i<arr.length();i++)
                    {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        fname = jsonObject.getString("firstName");
                        lname = jsonObject.getString("lastName");
                        titlePhoto = jsonObject.getString("titlePhoto");
                        country = jsonObject.getString("country");
                        rating = jsonObject.getInt("rating");
                        maxRating = jsonObject.getInt("maxRating");
                        friendOfCount = jsonObject.getInt("friendOfCount");
                        rank = jsonObject.getString("rank");
                        maxRank = jsonObject.getString("maxRank");
                        data.setText("Name: "+fname+" "+lname+"\n\n"+"Country: "+country+"\n\n"+"Max Rating: "+maxRating+"\n\n"+"Rating: "+rating+"\n\n"+"Max Rating: "+maxRating+"\n\n"+"Friend of: "+friendOfCount);

                        downloadimg d = new downloadimg();
                        Bitmap bitmap;
                        bitmap= d.execute(titlePhoto).get();
                        imageofuser.setImageBitmap(bitmap);
                    }

                } catch (JSONException e) {
                    imageofuser.setImageBitmap(null);
                    errorText.setText("Can't even copy a username properly? :(");
                    errorText.setVisibility(View.VISIBLE);
                    data.setText("");
                } catch (InterruptedException e) {
                    imageofuser.setImageBitmap(null);
                    errorText.setText("Can't even copy a username properly? :(");
                    errorText.setVisibility(View.VISIBLE);
                    data.setText("");
                } catch (ExecutionException e) {
                    imageofuser.setImageBitmap(null);
                    errorText.setText("Can't even copy a username properly? :(");
                    errorText.setVisibility(View.VISIBLE);
                    data.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageofuser.setImageBitmap(null);
                errorText.setText("Can't even copy a username properly? :(");
                errorText.setVisibility(View.VISIBLE);
                data.setText("");
            }
        });

        queue.add(jsonObjectRequest);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        data = findViewById(R.id.data);
        imageofuser = findViewById(R.id.imageofuser);
        errorText = findViewById(R.id.errortext);
    }
}