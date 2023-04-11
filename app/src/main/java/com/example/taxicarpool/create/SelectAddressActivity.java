package com.example.taxicarpool.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.taxicarpool.LoggedInUser;
import com.example.taxicarpool.R;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolUserCrossRef;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.join.Criteria;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class SelectAddressActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static String TAG = SelectAddressActivity.class.getSimpleName();
    private GoogleMap map;
    private LatLng coordinates;
    private Marker marker;


    Button create;

    RadioButton suv;
    RadioButton sedan;
    RadioButton truck;
    RadioButton van;

    CheckBox gender;
    CheckBox pets;        ;


    TextView text_match_id;
    String matchId;
    AutocompleteSupportFragment currentLocationSearch;
    AutocompleteSupportFragment destinationSearch;

    String currentLocation;
    String destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        String apiKey = getString(R.string.maps_api_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Initialize the AutocompleteSupportFragment.
         currentLocationSearch = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment_location);
         destinationSearch = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment_destination);

        currentLocationSearch.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        destinationSearch.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        currentLocationSearch.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                currentLocation = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);

            }
        });
        destinationSearch.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                destination = place.getName();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        Intent i = getIntent();
        text_match_id = findViewById(R.id.text_match_id);
        if (i != null && i.hasExtra("MY_STRING_EXTRA")) {
            matchId = i.getStringExtra("MY_STRING_EXTRA");
            text_match_id.setText("Match ID: " + matchId);

        }
        suv = findViewById(R.id.radio_suv);
        sedan = findViewById(R.id.radio_sedan);
        truck = findViewById(R.id.radio_truck);
        van = findViewById(R.id.radio_van);

        gender = findViewById(R.id.checkBox_gender);
        pets = findViewById(R.id.checkBox_pets);

        create = findViewById(R.id.create);
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

        Carpool carpool = new Carpool(Long.parseLong(matchId),currentLocation, destination,f,criteria);
        LoggedInUser user = new LoggedInUser();
//        CarpoolUserCrossRef carpoolUserCrossRef = new CarpoolUserCrossRef(carpool, user.getUser());

        EncryptionController encryptionController = EncryptionController.getInstance(getApplicationContext());
        encryptionController.insertCarpool(carpool);
//        encryptionController.insertCarpoolRef(carpoolUserCrossRef);

        System.out.println(encryptionController.getAllCarpool());

//        System.out.println(carpool.getMatchId());
//        System.out.println(carpool.getCurrentLocation());
//        System.out.println(carpool.getDestination());
//        System.out.println(carpool.getDistance());
        finish();


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a string resource.
            boolean success = true;

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15f));
        marker = map.addMarker(new MarkerOptions().position(coordinates));
    }

}