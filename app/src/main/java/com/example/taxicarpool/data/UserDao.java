package com.example.taxicarpool.data;

import androidx.room.Dao;
import androidx.room.Delete;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM useridentity")
    List<UserIdentity> getAll();

    @Query("SELECT * FROM useridentity WHERE uid in (:userIds)")
    List<UserIdentity> loadAllUsersByIds(int[] userIds);

    @Query("SELECT * FROM useridentity WHERE firstName LIKE :first AND " +
            "lastName LIKE :last LIMIT 1")
    UserIdentity findByName(String first, String last);

    @Query("SELECT * FROM useridentity WHERE email LIKE :email LIMIT 1")
    UserIdentity findByEmail(String email);


    @Insert
    Long insertUser(UserIdentity user);

    @Update
    void updateUser(UserIdentity user);

    @Delete
    void deleteUser(UserIdentity user);

    @Query("SELECT * FROM carpool")
    List<Carpool> getAllCarpool();

    @Query("SELECT * FROM carpool WHERE matchId in (:matchIds)")
    List<Carpool> loadAllCarpoolsByIds(int[] matchIds);

    @Query("SELECT * FROM Carpool WHERE matchId == :id")
    Carpool findCarpoolById(Long id);


    @Query("SELECT * FROM Carpool WHERE destination == :destination AND currentLocation == :currentLocation")
    List<Carpool> findCarpoolByTrip(String currentLocation, String destination);

    @Insert
    void insertCarpool(Carpool... carpool);

    @Insert
    void insertCarpoolUserRef(CarpoolUserCrossRef ref);

    @Update
    void updateCarpool(Carpool... carpool);

    @Delete
    void deleteCarpool(Carpool... carpool);

    @Transaction
    @Query("SELECT * FROM carpool WHERE matchId=(:matchId) LIMIT 1")
    CarpoolWithRiders getCarpoolWithRiders(Long matchId);

    @Transaction
    @Query("SELECT * FROM useridentity WHERE uid=(:id) LIMIT 1")
    RiderWithCarpools getRiderWithCarpools(Long id);
}
