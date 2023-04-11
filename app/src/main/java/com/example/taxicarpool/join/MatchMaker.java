package com.example.taxicarpool.join;

import android.content.Context;

import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolUserCrossRef;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

import java.util.List;
import java.util.stream.Collectors;

public class MatchMaker {
    Context context;

    public MatchMaker(Context context){
        this.context = context;
    }

    public Carpool findCarpool(UserIdentity user) throws Exception {
        List<Carpool> allCarpools = EncryptionController.getInstance(context).getAllCarpool();
        return null;
    }



    // Helper Function to compare criteria
    public boolean isCarpoolMatch(Criteria carpoolCriteria, Criteria searchCriteria) {
        // Check if the carpool vehicle type matches the search criteria
        boolean vehicleTypeMatch = false;

        if (carpoolCriteria.isSuv() && searchCriteria.isSuv()) {
            vehicleTypeMatch = true;
        } else if (carpoolCriteria.isVan() && searchCriteria.isVan()) {
            vehicleTypeMatch = true;
        } else if (carpoolCriteria.isSedan() && searchCriteria.isSedan()) {
            vehicleTypeMatch = true;
        } else if (carpoolCriteria.isTruck() && searchCriteria.isTruck()) {
            vehicleTypeMatch = true;
        }
        if (!vehicleTypeMatch) {
            return false;
        }

        // Check if the carpool pets allowed and gender safe options match the search criteria
        if (carpoolCriteria.isPets() != searchCriteria.isPets()) {
            return false;
        }
        if (carpoolCriteria.isGender() != searchCriteria.isGender()) {
            return false;
        }

        return true;
    }
    // Get the filtered carpool results based on the search query
    public List<Carpool> getCarpoolSearchResults(String currentLocation, String destination, Criteria criteria) throws Exception {
        List<Carpool> allCarpools = EncryptionController.getInstance(context).getAllCarpool();
        List<Carpool> filteredCarpools = allCarpools.stream()
                .filter(carpool ->
                        carpool.getCurrentLocation() == currentLocation &&
                                carpool.getDestination() == destination &&
                                isCarpoolMatch(carpool.getCriteria(), criteria)
                        )
                .collect(Collectors.toList());
        return filteredCarpools;
    }


    // Update the DB by adding a carpoolusercrossref and updating carpool
    public void handleSelection(Carpool selectedCarpool, UserIdentity user) throws Exception {
        CarpoolUserCrossRef entry = new CarpoolUserCrossRef(selectedCarpool, user);
        EncryptionController e = new EncryptionController();
        e.getInstance(context).insertCarpoolRef(entry);
    }
}
