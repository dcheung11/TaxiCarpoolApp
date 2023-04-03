package com.example.taxicarpool.data;

import androidx.room.Entity;

@Entity(primaryKeys = {"matchId", "uid"})
public class CarpoolUserCrossRef {
    public long matchId;
    public long uid;
}
