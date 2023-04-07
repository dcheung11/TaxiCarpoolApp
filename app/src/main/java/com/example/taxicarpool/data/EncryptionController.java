package com.example.taxicarpool.data;

import android.content.Context;

import java.util.List;

public class EncryptionController {

    private static EncryptionController instance;
    private static AppDatabase db;
    private static UserDao dao;



    public static synchronized EncryptionController getInstance(Context context) {

        if (instance == null) {
            instance = new EncryptionController();
            db = AppDatabase.getInstance(context);
            dao = db.Dao();
        }
        return instance;
    }

    public Long insertUser(UserIdentity user){
        return dao.insertUser(user);
    }

    public void updateUser(UserIdentity user){
        dao.updateUser(user);
    }

    public void deleteUser(UserIdentity user){
        dao.deleteUser(user);
    }

    public List<UserIdentity> getAll(){
        return dao.getAll();
    }

    public UserIdentity findByName(String firstName, String lastName){
        // insert encryption here
       return dao.findByName(firstName,lastName);
    }

//    public void insertCarpool(Carpool carpool){
//        // insert encryption here
//        dao.insertAll(carpool);
//    }

    public void encrypt(){

    }

}
