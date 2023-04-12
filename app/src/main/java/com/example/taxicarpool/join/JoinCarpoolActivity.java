package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taxicarpool.R;
import com.example.taxicarpool.join.FindLocationActivity;

public class JoinCarpoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);

    }

    public void getLocation(View V){
        Intent i = new Intent(this, FindLocationActivity.class);
        startActivity(i);
    }

}