package com.example.taxicarpool.data;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class RiderWithCarpools {
    @Embedded
    public UserIdentity rider;
    @Relation(
            parentColumn = "uid",
            entityColumn = "matchId",
            associateBy = @Junction(CarpoolUserCrossRef.class)
    )
    public List<Carpool> carpools;

    public UserIdentity getRider() {
        return rider;
    }

    public List<Carpool> getCarpools() {
        return carpools;
    }
}
