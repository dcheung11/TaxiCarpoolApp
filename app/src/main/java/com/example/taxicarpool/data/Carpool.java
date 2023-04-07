package com.example.taxicarpool.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carpool carpool = (Carpool) o;
        return matchId.equals(carpool.matchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId);
    }

    @Override
    public String toString() {
        return "Carpool{" +
                "matchId=" + matchId +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                '}';
    }
}
