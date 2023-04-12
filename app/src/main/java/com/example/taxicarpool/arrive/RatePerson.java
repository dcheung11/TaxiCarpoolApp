package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;


import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.RatingBar;
import com.example.taxicarpool.MainActivity;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolWithRiders;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.RiderWithCarpools;
import com.example.taxicarpool.data.UserIdentity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class RatePerson extends AppCompatActivity {
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_person);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        ((TextView)findViewById(R.id.textView6)).setText("Rating: " + name);
        rating = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
    }

    public void ratingSuccess(View v) throws Exception {
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        int numberOfStars = (int) rating.getRating(); // what the passenger rated
        UpdateRating(numberOfStars,name);
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rateLayout),"Rating Submitted!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public void BackHome(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void UpdateRating(int numStars, String name) throws Exception {
        EncryptionController e1 = EncryptionController.getInstance(getApplicationContext());
        LoggedInUser l1 = LoggedInUser.getInstance();
        UserIdentity user = l1.getUser();  //logged in User
        RiderWithCarpools riderWithCarpools = e1.riderWithCarpools(user.getUid());
        List<Carpool> carpools = riderWithCarpools.getCarpools();
        List<UserIdentity> u1 = null;

        for (Carpool carpool : carpools) {
            CarpoolWithRiders carpoolWithRiders = e1.carpoolWithRiders(carpool.matchId);
            u1 = carpoolWithRiders.getUsers();
            if (u1.contains(user)) {
                break;
            }
        }

        for (UserIdentity u: u1){
            if ((u.getFirstName() + " " + u.getLastName()).equals(name)){
                u.setRating(numStars);
                e1.updateUser(u);
            }
        }


    }
}