package com.example.taxicarpool.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText

import com.example.taxicarpool.R;
import com.example.taxicarpool.join.JoinCarpoolActivity;

public class FindLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);
    }

    public void returnToSearch(View V){
        EditText t = findViewById(R.id.requesterLocation);
        String input = t.getText().toString();

        Intent i = new Intent(this, JoinCarpoolActivity.class);
        startActivity(i);
    }
}