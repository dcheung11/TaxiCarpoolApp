package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;

import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.join.MatchMaker;
import com.example.taxicarpool.join.JoinCarpoolActivity;
import com.example.taxicarpool.join.SelectCriteriaActivity;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    List<Carpool> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        MatchMaker matchmaker = new MatchMaker(this);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();


        Criteria criteria = new Criteria(bundle.getBoolean("SUV Criteria"), bundle.getBoolean("Sedan Criteria"), bundle.getBoolean("Truck Criteria"), bundle.getBoolean("Van Criteria"), bundle.getBoolean("Gender Criteria"), bundle.getBoolean("Pets Criteria"));
        try {
            searchResults = matchmaker.getCarpoolSearchResults(bundle.getString("Current Location"), bundle.getString("Destination Location"), criteria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void returnToJoin(View V){
        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }

    public void carpoolSelected(View V){
        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }
    
}