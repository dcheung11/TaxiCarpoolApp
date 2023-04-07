package com.example.taxicarpool.data;

import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity(primaryKeys = {"matchId", "uid"})
public class CarpoolUserCrossRef {
    public long matchId;
    public long uid;

    public CarpoolUserCrossRef(long matchId, long uid) {
        this.matchId = matchId;
        this.uid = uid;
    }

    public CarpoolUserCrossRef(Carpool carpool, UserIdentity user) {
        this.matchId = carpool.getMatchId();
        this.uid = user.getUid();
    }

    public static List<CarpoolUserCrossRef> CarpoolUserRefs(Carpool carpool, UserIdentity... users) {
        List<CarpoolUserCrossRef> list = new ArrayList<>();
        for (UserIdentity user: users) {
            list.add(new CarpoolUserCrossRef(carpool,user));
        }
        return list;
    }
}
