package com.example.taxicarpool.create;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapDestinationActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText currentLocation;
    EditText destination;
    Button button_create;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    TextView textview_match_id;
    String matchId;

//    textViewMatchId.setText(matchId);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_destination);
        Intent i = getIntent();
//        textview_match_id = findViewById(R.id.textview_match_id);
//        if (i != null && i.hasExtra("MY_STRING_EXTRA")) {
//            matchId = i.getStringExtra("MY_STRING_EXTRA");
//            textview_match_id.setText("Match ID: " + matchId);
//
//        }
//        currentLocation = findViewById(R.id.currentLocation);
//        destination = findViewById(R.id.destination);
//        button_create = findViewById(R.id.button_create);

    }

    public void handleCreate(View v) throws Exception{
        float f = 10;
        Carpool carpool = new Carpool(Long.parseLong(matchId), currentLocation.getText().toString(), destination.getText().toString(),f);
        EncryptionController encryptionController = EncryptionController.getInstance(getApplicationContext());
        encryptionController.insertCarpool(carpool);
        System.out.println(encryptionController.getAllCarpool());

//        System.out.println(carpool.getMatchId());
//        System.out.println(carpool.getCurrentLocation());
//        System.out.println(carpool.getDestination());
//        System.out.println(carpool.getDistance());
        finish();


    }


}