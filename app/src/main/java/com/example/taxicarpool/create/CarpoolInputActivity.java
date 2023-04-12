package com.example.taxicarpool.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CarpoolInputActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener {
    private static final String TAG = CarpoolInputActivity.class.getSimpleName();
    private GoogleMap map;
    private Marker startMarker;
    private Marker endMarker;
     private Polyline polyline;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean permissionDenied = false;
    Button create;
    RadioButton suv;
    RadioButton sedan;
    RadioButton truck;
    RadioButton van;
    CheckBox gender;
    CheckBox pets;
    TextView text_match_id;
    String matchId;
    AutocompleteSupportFragment currentLocationSearch;
    AutocompleteSupportFragment destinationSearch;

    LatLng currentCoordinates;
    LatLng destinationCoordinates;
    String currentLocation;
    String destination;
    float distance;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpool_details);
        String apiKey = getString(R.string.maps_key);

        //         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.confirmation_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

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


        // Getting place from search
        currentLocationSearch.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getLatLng() + ", " + place.getAddress());
                currentLocation = place.getName();

                // Use the Google Places API to fetch details about the place
                PlacesClient placesClient = Places.createClient(getApplicationContext());
                List<Place.Field> placeFields = Collections.singletonList(Place.Field.LAT_LNG);
                FetchPlaceRequest request = FetchPlaceRequest.newInstance(Objects.requireNonNull(place.getId()), placeFields);

                Task<FetchPlaceResponse> placeTask = placesClient.fetchPlace(request);
                placeTask.addOnSuccessListener((response) -> {
                    // Get the place's LatLng coordinates
                    LatLng latLng = response.getPlace().getLatLng();
                    assert latLng != null;
                    Log.d(TAG, "Place coordinates: " + latLng);
                    currentCoordinates = latLng;
                    if (startMarker != null) {
                        startMarker.remove();
                    }
                    startMarker = map.addMarker(new MarkerOptions().position(latLng).title(currentLocation));

                    // Move the camera to the new marker's location
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }).addOnFailureListener((exception) -> {
                    // Handle any errors that occur while fetching the place
                    Log.e(TAG, "Place not found: " + exception.getMessage());
                });
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);

            }
        });
        destinationSearch.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                destination = place.getName();
                // Use the Google Places API to fetch details about the place
                PlacesClient placesClient = Places.createClient(getApplicationContext());
                List<Place.Field> placeFields = Collections.singletonList(Place.Field.LAT_LNG);
                FetchPlaceRequest request = FetchPlaceRequest.newInstance(Objects.requireNonNull(place.getId()), placeFields);

                Task<FetchPlaceResponse> placeTask = placesClient.fetchPlace(request);
                placeTask.addOnSuccessListener((response) -> {
                    // Get the place's LatLng coordinates
                    LatLng latLng = response.getPlace().getLatLng();
                    assert latLng != null;
                    Log.d(TAG, "Place coordinates: " + latLng);
                    destinationCoordinates = latLng;
                    if (endMarker != null) {
                        endMarker.remove();
                    }
                    endMarker = map.addMarker(new MarkerOptions().position(latLng).title(destination));
                    if (polyline != null) {
                        polyline.remove();
                    }
                    if (currentCoordinates != null && destinationCoordinates != null) {
                        polyline = map.addPolyline(new PolylineOptions()
                                .clickable(true)
                                .add(currentCoordinates, destinationCoordinates));

                    }

                }).addOnFailureListener((exception) -> {
                    // Handle any errors that occur while fetching the place
                    Log.e(TAG, "Place not found: " + exception.getMessage());
                });
            }

            @Override
            public void onError(@NonNull Status status) {
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
        Criteria criteria = new Criteria(suv.isChecked(), sedan.isChecked(),truck.isChecked(), van.isChecked(), gender.isChecked(),pets.isChecked());
        float[] results = new float[1];

        if (isValidCarpool(currentLocation,destination, criteria)){
            Location.distanceBetween(currentCoordinates.latitude, currentCoordinates.longitude, destinationCoordinates.latitude, destinationCoordinates.longitude,results);
            distance = results[0]/1000; // Get km distance
            Carpool carpool = new Carpool(Long.parseLong(matchId),currentLocation,destination,distance,criteria);
            CarpoolUserCrossRef carpoolUserCrossRef = new CarpoolUserCrossRef(carpool, LoggedInUser.getInstance().getUser());
            EncryptionController encryptionController = EncryptionController.getInstance(getApplicationContext());
            encryptionController.insertCarpool(carpool);
            encryptionController.insertCarpoolRef(carpoolUserCrossRef);
            System.out.println(encryptionController.getAllCarpool());
            Toast.makeText(this, "Carpool created!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Missing values in form!", Toast.LENGTH_SHORT).show();
            create.setError("Missing values in form!");
        }




    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        LatLng mcmaster = new LatLng( 43.2609, -79.9192);

        map.moveCamera(CameraUpdateFactory.newLatLng(mcmaster));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));

        enableMyLocation(map);
    }

    @SuppressLint("MissingPermission")
    private void enableMyLocation(GoogleMap googleMap) {
        // [START maps_check_location_permission]
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
            return;
        }

        // 2. Otherwise, request location permissions from the user.
        PermissionUtils.requestLocationPermissions(this, LOCATION_PERMISSION_REQUEST_CODE, true);
        // [END maps_check_location_permission]
    }
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION) || PermissionUtils
                .isPermissionGranted(permissions, grantResults,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation(map);
        } else {
            // Permission was denied. Display an error message
            // [START_EXCLUDE]
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true;
            // [END_EXCLUDE]
        }
    }
    // [END maps_check_location_permission_result]

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    public boolean isValidCarpool(String currentLocation, String destination, Criteria criteria) {
        boolean locationSelected = currentLocation != null && destination != null;
        boolean carTypeSelected = criteria.isSuv() || criteria.isSedan() || criteria.isTruck() || criteria.isVan();
        return locationSelected && carTypeSelected;

    }

}