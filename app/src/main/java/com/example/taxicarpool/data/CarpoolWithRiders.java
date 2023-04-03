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
            entityColumn = "userId",
            associateBy = @Junction(CarpoolUserCrossRef.class)
    )
    public List<UserIdentity> users;
}
