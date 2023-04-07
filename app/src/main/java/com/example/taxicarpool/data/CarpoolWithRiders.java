package com.example.taxicarpool.data;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CarpoolWithRiders {
    @Embedded public Carpool carpool;
    @Relation(
            parentColumn = "matchId",
            entityColumn = "uid",
            associateBy = @Junction(CarpoolUserCrossRef.class)
    )
    public List<UserIdentity> users;

    public Carpool getCarpool() {
        return carpool;
    }

    public void setCarpool(Carpool carpool) {
        this.carpool = carpool;
    }

    public List<UserIdentity> getUsers() {
        return users;
    }

}
