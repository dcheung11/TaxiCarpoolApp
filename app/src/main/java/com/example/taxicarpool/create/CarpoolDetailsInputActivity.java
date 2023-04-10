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
        Intent i = getIntent();
        textview_match_id = findViewById(R.id.textview_match_id);
        if (i != null && i.hasExtra("MY_STRING_EXTRA")) {
            matchId = i.getStringExtra("MY_STRING_EXTRA");
            textview_match_id.setText("Match ID: " + matchId);

        }
        currentLocation = findViewById(R.id.currentLocation);
        destination = findViewById(R.id.destination);
        suv = findViewById(R.id.radio_suv);
        sedan = findViewById(R.id.radio_sedan);
        truck = findViewById(R.id.radio_truck);
        van = findViewById(R.id.radio_van);

        gender = findViewById(R.id.checkbox_gender);
        pets = findViewById(R.id.checkBox_pets);

        button_create = findViewById(R.id.button_create);

    }

    public void handleCreate(View v) throws Exception{
        float f = 10;
        Criteria criteria = new Criteria();
        criteria.setSuv(suv.isChecked());
        criteria.setSedan(sedan.isChecked());
        criteria.setTruck(truck.isChecked());
        criteria.setVan(van.isChecked());
        criteria.setGender(gender.isChecked());
        criteria.setPets(pets.isChecked());

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