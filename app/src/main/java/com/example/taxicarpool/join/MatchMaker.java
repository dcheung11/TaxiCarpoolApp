package com.example.taxicarpool.join;

import android.content.Context;

import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.EncryptionController;
import com.example.taxicarpool.data.UserIdentity;

import java.util.List;

public class MatchMaker {
    Context context;

    public MatchMaker(Context context){
        this.context = context;
    }

    public Carpool findCarpool(UserIdentity user) throws Exception {
        List<Carpool> allCarpools = EncryptionController.getInstance(context).getAllCarpool();
        return null;
    }

//    public float calculateDistance(String userLocation, String carpoolLocation){
//
//        return null;
//    }
}
