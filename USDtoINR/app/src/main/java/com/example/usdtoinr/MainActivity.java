package com.example.usdtoinr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void convert(View view)
    {
        EditText USD = (EditText) findViewById(R.id.USD);
        double usd = Double.parseDouble(USD.getText().toString());
        double inr = usd*74.82;
        String ans = String.format("%.2f",inr);
        Toast.makeText(this, usd + " USD is equal to "+ ans +"Rupees", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}