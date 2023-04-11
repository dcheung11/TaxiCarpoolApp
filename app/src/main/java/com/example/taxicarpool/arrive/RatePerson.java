package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;


import com.example.taxicarpool.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.RatingBar;
import com.example.taxicarpool.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class RatePerson extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_person);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        ((TextView)findViewById(R.id.textView6)).setText("Rating: " + name);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        int numberOfStars = rating.getNumStars(); // what the passenger rated
    }

    public void ratingSuccess(View v){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rateLayout),"Rating Submitted!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public void BackHome(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}