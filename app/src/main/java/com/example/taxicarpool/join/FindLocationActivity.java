package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taxicarpool.R;
import com.example.taxicarpool.join.JoinCarpoolActivity;

public class FindLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);
    }

    public void returnToSearch(View V){
        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }
}