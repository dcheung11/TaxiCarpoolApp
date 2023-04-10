package com.example.taxicarpool.join;

import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

import java.util.List;

public class MatchMaker {

    public Carpool findCarpool(UserIdentity user){
        return null;
    }


    // Get the filtered carpool results based on the search query
    public List<Carpool> getCarpoolSearchResults(List<Carpool> carpool, Criteria criteria){
        return carpool;
    }


    // Update the DB by adding a carpoolusercrossref and updating carpool
    public void handleSelection(){
//        return null;
    }
}
