package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.MainActivity;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolWithRiders;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.RiderWithCarpools;
import com.example.taxicarpool.data.UserIdentity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class PayNow extends AppCompatActivity {

    Float distance;
    int num_riders = 0;

    public float FareSplit() throws Exception {
        EncryptionController e1 = EncryptionController.getInstance(getApplicationContext());
        LoggedInUser l1 = LoggedInUser.getInstance();
        UserIdentity user = l1.getUser();  //logged in User
        RiderWithCarpools riderWithCarpools = e1.riderWithCarpools(user.getUid());
        List<Carpool> carpools = riderWithCarpools.getCarpools();
        float fare;
        List<UserIdentity> u1 = null;

        for (Carpool carpool : carpools) {
            CarpoolWithRiders carpoolWithRiders = e1.carpoolWithRiders(carpool.matchId);
            u1 = carpoolWithRiders.getUsers();
            if (u1.contains(user.getUid())) {
                distance = carpool.getDistance();
                num_riders = u1.size();
                break;
            }
        }
        System.out.println("Distance: " + distance);
        System.out.println("Users: " + u1);
        System.out.println("Carpool: " + carpools);
        fare = distance / num_riders;
        return fare;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
        float fare;
        try {
            fare = FareSplit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String f = Float.toString(fare);
        ((TextView) findViewById(R.id.textView9)).setText(f);
    }

    public void RatePassengers(View v) {
        Intent i = new Intent(this, RatePassengers.class);
        startActivity(i);
    }

    public void BackHome(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}