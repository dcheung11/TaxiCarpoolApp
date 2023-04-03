package com.example.taxicarpool.data;

import androidx.room.Dao;
import androidx.room.Delete;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM useridentity")
    List<UserIdentity> getAll();

    @Query("SELECT * FROM useridentity WHERE uid in (:userIds)")
    List<UserIdentity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM useridentity WHERE firstName LIKE :first AND " +
            "lastName LIKE :last LIMIT 1")
    UserIdentity findByName(String first, String last);

    @Insert
    void insertAll(UserIdentity... users);

    @Update
    void updateUser(UserIdentity user);

    @Delete
    void delete(UserIdentity user);
}
