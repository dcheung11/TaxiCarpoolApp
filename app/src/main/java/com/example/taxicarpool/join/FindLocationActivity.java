package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolUserCrossRef;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.join.Criteria;
import com.example.taxicarpool.join.SelectCriteriaActivity;
import com.example.taxicarpool.profile.ProfileActivity;
import com.google.android.libraries.places.api.Places;

public class FindLocationActivity extends AppCompatActivity {
    EditText currentLocation;
    EditText destination;
    Button submit;
    String currentLoc;
    String destinationLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);
        String apiKey = getString(R.string.maps_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */

    }

    public void handleSubmit(View v){
        currentLocation = findViewById(R.id.requesterLocation);

        destination = findViewById(R.id.requesterDestination);

        currentLoc = currentLocation.getText().toString();
        destinationLoc = destination.getText().toString();

        Intent i = new Intent(this, SelectCriteriaActivity.class);

        i.putExtra("Current Location", currentLoc);
        i.putExtra("Destination Location", destinationLoc);

        startActivity(i);
    }

}
