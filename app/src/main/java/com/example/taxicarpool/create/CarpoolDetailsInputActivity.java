package com.example.taxicarpool.create;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolUserCrossRef;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.join.Criteria;
import com.google.android.libraries.places.api.Places;

public class CarpoolDetailsInputActivity extends AppCompatActivity  {

    EditText currentLocation;
    EditText destination;
    Button button_create;

    RadioButton suv;
    RadioButton sedan;
    RadioButton truck;
    RadioButton van;

    CheckBox gender;
    CheckBox pets;        ;


    TextView textview_match_id;
    String matchId;

//    textViewMatchId.setText(matchId);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carpool_details_input);
        String apiKey = getString(R.string.maps_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);

    }

    public void handleCreate(View v) throws Exception{
        float f = 10;
        Criteria criteria = new Criteria(suv.isChecked(), sedan.isChecked(),truck.isChecked(), van.isChecked(), gender.isChecked(),pets.isChecked());

        Carpool carpool = new Carpool(Long.parseLong(matchId),currentLocation.getText().toString(), destination.getText().toString(),f,criteria);
//        LoggedInUser user = new LoggedInUser()
        CarpoolUserCrossRef carpoolUserCrossRef = new CarpoolUserCrossRef(carpool.getMatchId(), 5);

        EncryptionController encryptionController = EncryptionController.getInstance(getApplicationContext());
        encryptionController.insertCarpool(carpool);
        encryptionController.insertCarpoolRef(carpoolUserCrossRef);

//        System.out.println(encryptionController.getAllCarpool());

//        System.out.println(carpool.getMatchId());
//        System.out.println(carpool.getCurrentLocation());
//        System.out.println(carpool.getDestination());
//        System.out.println(carpool.getDistance());
        finish();


    }


}