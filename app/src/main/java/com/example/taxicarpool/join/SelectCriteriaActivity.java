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
        Criteria criteria = new Criteria();

        // Concatenation of the checked options in if

        // isChecked() is used to check whether
        // the CheckBox is in true state or not.

        if(checkbox_suv.isChecked()){
            criteria.setSuv(true);
            msg = msg + " SUV ";
        }
        else
            criteria.setSuv(false);
        if(checkbox_sedan.isChecked()){
            criteria.setSedan(true);
            msg = msg + " Sedan ";
        }
        else
            criteria.setSedan(false);
        if(checkbox_truck.isChecked()){
            criteria.setTruck(true);
            msg = msg + " Truck ";
        }
        else
            criteria.setTruck(false);
        if(checkbox_van.isChecked()){
            criteria.setVan(true);
            msg = msg + " Van ";
        }
        else
            criteria.setVan(false);
        if(checkbox_gender.isChecked()){
            criteria.setGender(true);
            msg = msg + " Same-gendered only ";
        }
        else
            criteria.setGender(false);
        if(checkbox_pets.isChecked()){
            criteria.setPets(true);
            msg = msg + " pets allowed ";
        }
        else
            criteria.setPets(false);



        // Toast is created to display the
        // message using show() method.
        Toast.makeText(this, msg + "are selected",
                Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }

}