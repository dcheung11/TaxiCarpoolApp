package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.MainActivity;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PayNow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
    }

    public void RatePassengers(View v){
        Intent i = new Intent(this, RatePassengers.class);
        startActivity(i);
    }

    public void BackHome(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}