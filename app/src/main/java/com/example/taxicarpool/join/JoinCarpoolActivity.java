package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.taxicarpool.R;

public class JoinCarpoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_carpool);
    }

    public void launchCriteriaSelection(View V){
        Intent i = new Intent(this, SelectCriteriaActivity.class);
        startActivity(i);
    }

    public void launchSearchResults(View v) {
        Intent i = new Intent(this, SearchResultsActivity.class);
        startActivity(i);
    }

    public void getLocation(View V){
        Intent i = new Intent(this, FindLocationActivity.class);
        startActivity(i);
    }

}