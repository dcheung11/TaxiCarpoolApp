package com.example.taxicarpool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.taxicarpool.arrive.ArrivalActivity;
import com.example.taxicarpool.create.CreateCarpoolActivity;
import com.example.taxicarpool.join.JoinCarpoolActivity;
import com.example.taxicarpool.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openProfile(View v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void openArrive(View v){
        if (!LoggedInUser.getInstance().isLoggedIn()){
            Toast.makeText(this, "Must be Logged In", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this,ArrivalActivity.class);
            startActivity(i);
        }
    }

    public void openCreate(View v){
        Intent i = new Intent(this, CreateCarpoolActivity.class);
        startActivity(i);
    }

    public void openJoin(View v){
        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }


}