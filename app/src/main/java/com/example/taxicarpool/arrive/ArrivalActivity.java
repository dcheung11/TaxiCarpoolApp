package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.taxicarpool.R;
import com.example.taxicarpool.profile.RegisterActivity;

public class ArrivalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);
    }

    public void PayNow(View v){
        Intent i = new Intent(this, PayNow.class);
        startActivity(i);
    }

}