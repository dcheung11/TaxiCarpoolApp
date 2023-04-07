package com.example.taxicarpool;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.taxicarpool.data.AppDatabase;
import com.example.taxicarpool.data.UserDao;
import com.example.taxicarpool.data.UserIdentity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class DatabaseTests {
    private UserDao userDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.Dao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeAndReadUser(){
        UserIdentity user = new UserIdentity(1, "Justin", "Dang", "testEmail@email.com","password");
        Long id = userDao.insertUser(user);
        assert user.getUid()==id;
        UserIdentity obtainedUser = userDao.findByName("Justin","Dang");
        assert user.equals(obtainedUser);

    }

}
