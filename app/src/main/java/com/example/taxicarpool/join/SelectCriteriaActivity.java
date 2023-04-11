package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.taxicarpool.R;
import com.example.taxicarpool.join.JoinCarpoolActivity;
import com.example.taxicarpool.join.Criteria;

public class SelectCriteriaActivity extends AppCompatActivity {

    CheckBox checkbox_suv;
    CheckBox checkbox_sedan;
    CheckBox checkbox_truck;
    CheckBox checkbox_van;
    CheckBox checkbox_gender;
    CheckBox checkbox_pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_criteria);
        checkbox_suv = (CheckBox) findViewById(R.id.checkbox_suv);
        checkbox_suv.setChecked(true);
        checkbox_sedan = (CheckBox) findViewById(R.id.checkbox_sedan);
        checkbox_sedan.setChecked(true);
        checkbox_truck = (CheckBox) findViewById(R.id.checkbox_truck);
        checkbox_truck.setChecked(true);
        checkbox_van = (CheckBox) findViewById(R.id.checkbox_van);
        checkbox_van.setChecked(true);
        checkbox_gender = (CheckBox) findViewById(R.id.checkbox_gender);
        checkbox_gender.setChecked(false);
        checkbox_pets = (CheckBox) findViewById(R.id.checkbox_pets);
        checkbox_pets.setChecked(false);
    }

    // This function is invoked when the button is pressed.
    public void returnToSearch(View V){
        String msg="";


        // isChecked() is used to check whether
        // the CheckBox is in true state or not.

        Criteria critera = new Criteria(checkbox_suv.isChecked(), checkbox_sedan.isChecked(), checkbox_truck.isChecked(), checkbox_van.isChecked(), checkbox_gender.isChecked(), checkbox_pets.isChecked());



        // Toast is created to display the
        // message using show() method.
        Toast.makeText(this, msg + "are selected",
                Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }

}