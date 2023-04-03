package com.example.taxicarpool.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Carpool {

    @PrimaryKey
    public Long matchId;

    public String destination;

    public Float distance;

    public Carpool(Long matchId, String destination, Float distance) {
        this.matchId = matchId;
        this.destination = destination;
        this.distance = distance;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }
}
