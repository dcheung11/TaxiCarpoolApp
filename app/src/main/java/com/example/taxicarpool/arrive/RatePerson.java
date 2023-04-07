package com.example.taxicarpool.arrive;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taxicarpool.R;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class RatePerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_person);
        Intent i = getIntent();
        String name = i.getStringExtra("NAME");
        ((TextView)findViewById(R.id.textView6)).setText(name);
    }
}