package com.example.taxicarpool.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void goToRegister(View v){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void goToProfile(View v){
        if (LoggedInUser.getInstance().isLoggedIn()) {
            Intent i = new Intent(this, ProfileScreen.class);
            startActivity(i);
        } else{
            Toast.makeText(this, "Not Logged In", Toast.LENGTH_SHORT).show();
        }
    }
}