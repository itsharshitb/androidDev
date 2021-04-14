package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public void validate(View view)
    {
        EditText username = (EditText) findViewById(R.id.userName);
        EditText password = (EditText) findViewById(R.id.password);
        Log.i("Info","Button pressed");
        Log.i("Username",username.getText().toString());
        Log.i("Password",password.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}