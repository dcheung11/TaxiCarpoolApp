package com.example.taxicarpool;

import com.example.taxicarpool.data.UserIdentity;

public class LoggedInUser {

    private static LoggedInUser instance;
    private UserIdentity user;

    public static LoggedInUser getInstance(){
        if (instance==null){
            instance = new LoggedInUser();
        }
        return instance;
    }

    public boolean isLoggedIn(){
        return user!=null;
    }

    public UserIdentity getUser() {
        return user;
    }

    public void login(UserIdentity user){
        this.user =user;
    }

    public void logoff(){
        user = null;
    }
}
