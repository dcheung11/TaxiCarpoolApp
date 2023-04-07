package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taxicarpool.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RatePassengers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_passengers);
    }

    public void RatePerson(View v){
        Intent i = new Intent(this, RatePerson.class);
        String name = ((Button)v).getText().toString();
        i.putExtra("NAME", name);
        startActivity(i);
    }

}