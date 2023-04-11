package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.MainActivity;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PayNow extends AppCompatActivity {

    EncryptionController e1 = new EncryptionController();
    LoggedInUser l1 = new LoggedInUser();
    UserIdentity user = l1.getUser();  //logged in User

    public PayNow() throws Exception {
    }

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