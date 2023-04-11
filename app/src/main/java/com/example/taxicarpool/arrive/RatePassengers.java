package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolWithRiders;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.RiderWithCarpools;
import com.example.taxicarpool.data.UserIdentity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RatePassengers extends AppCompatActivity {

    public List<String> SetNames() throws Exception {
        EncryptionController e1 = EncryptionController.getInstance(getApplicationContext());
        LoggedInUser l1 = LoggedInUser.getInstance();
        UserIdentity user = l1.getUser();  //logged in User
        RiderWithCarpools riderWithCarpools = e1.riderWithCarpools(user.getUid());
        List<Carpool> carpools = riderWithCarpools.getCarpools();
        List<UserIdentity> u1 = null;
        List<String> names = new ArrayList<>();

        for (Carpool carpool : carpools) {
            CarpoolWithRiders carpoolWithRiders = e1.carpoolWithRiders(carpool.matchId);
            u1 = carpoolWithRiders.getUsers();
            if (u1.contains(user)) {
                break;
            }
        }

        for (UserIdentity users : u1) {
            if (users.getUid()!=user.getUid()) {
                names.add(users.getFirstName() + " " + users.getLastName());
            }
        }
        System.out.println("names:" + names);

        return names;
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_passengers);
        List<String> riders = null;
            try {
                riders = SetNames();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (riders.size()==5){
                ((Button) findViewById(R.id.button)).setText(riders.get(0));
                ((Button) findViewById(R.id.button3)).setText(riders.get(1));
                ((Button) findViewById(R.id.button4)).setText(riders.get(2));
                ((Button) findViewById(R.id.button5)).setText(riders.get(3));
                ((Button) findViewById(R.id.button6)).setText(riders.get(4));
            }
            else if (riders.size()==4){
                ((Button) findViewById(R.id.button)).setText(riders.get(0));
                ((Button) findViewById(R.id.button3)).setText(riders.get(1));
                ((Button) findViewById(R.id.button4)).setText(riders.get(2));
                ((Button) findViewById(R.id.button5)).setText(riders.get(3));
                ((Button) findViewById(R.id.button6)).setText(" ");
            }
            else if (riders.size()==3){
                ((Button) findViewById(R.id.button)).setText(riders.get(0));
                ((Button) findViewById(R.id.button3)).setText(riders.get(1));
                ((Button) findViewById(R.id.button4)).setText(riders.get(2));
                ((Button) findViewById(R.id.button5)).setText(" ");
                ((Button) findViewById(R.id.button6)).setText(" ");
            }
            else if (riders.size()==2){
                ((Button) findViewById(R.id.button)).setText(riders.get(0));
                ((Button) findViewById(R.id.button3)).setText(riders.get(1));
                ((Button) findViewById(R.id.button4)).setText(" ");
                ((Button) findViewById(R.id.button5)).setText(" ");
                ((Button) findViewById(R.id.button6)).setText(" ");
            }
            else if (riders.size()==1) {
                ((Button) findViewById(R.id.button)).setText(riders.get(0));
                ((Button) findViewById(R.id.button3)).setText(" ");
                ((Button) findViewById(R.id.button4)).setText(" ");
                ((Button) findViewById(R.id.button5)).setText(" ");
                ((Button) findViewById(R.id.button6)).setText(" ");
            }
            else {

            }

    }

    public void RatePerson(View v){
        Intent i = new Intent(this, RatePerson.class);
        String name = ((Button)v).getText().toString();
        i.putExtra("NAME", name);
        startActivity(i);
    }

}