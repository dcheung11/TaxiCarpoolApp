package com.example.taxicarpool.data;

import androidx.room.Entity;

@Entity(primaryKeys = {"matchId", "userId"})
public class CarpoolUserCrossRef {
    public long matchId;
    public long userId;
}
