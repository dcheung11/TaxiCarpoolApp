package com.example.taxicarpool;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.taxicarpool.data.AppDatabase;
import com.example.taxicarpool.data.Carpool;
import com.example.taxicarpool.data.CarpoolUserCrossRef;
import com.example.taxicarpool.data.RiderWithCarpools;
import com.example.taxicarpool.data.UserDao;
import com.example.taxicarpool.data.UserIdentity;
import com.example.taxicarpool.join.Criteria;

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
        UserIdentity user = new UserIdentity( "Justin", "Dang", "testEmail@email.com","password");
        Long id = userDao.insertUser(user);
        user.setUid(id);
        assert user.getUid()==id;
        UserIdentity obtainedUser = userDao.findByName("Justin","Dang");
        assert user.equals(obtainedUser);
    }

    @Test
    public void writeAndReadCarpool(){
        Criteria van_pets = new Criteria(false,false,false,true,false,true);
        Carpool itb_lazeez_1 = new Carpool(1L,"Information Technology Building","Lazeez Shawarma", 3.0F, van_pets);
        userDao.insertCarpool(itb_lazeez_1);
        assert  itb_lazeez_1.equals(userDao.getAllCarpool().get(0));
        assert userDao.getAllCarpool().get(0).getDistance()== 3.0F;
    }

    @Test
    public void submitBoth(){
        UserIdentity user = new UserIdentity( "Justin", "Dang", "testEmail@email.com","password");
        user.setUid(userDao.insertUser(user));
        Criteria van_pets = new Criteria(false,false,false,true,false,true);
        Carpool itb_lazeez_1 = new Carpool(1L,"Information Technology Building","Lazeez Shawarma", 3.0F, van_pets);
        userDao.insertCarpool(itb_lazeez_1);
        userDao.insertCarpoolUserRef(new CarpoolUserCrossRef(itb_lazeez_1,user));
        RiderWithCarpools riderWithCarpools = userDao.getRiderWithCarpools(user.getUid());
        assert user.equals(riderWithCarpools.getRider());
        assert itb_lazeez_1.equals(riderWithCarpools.getCarpools().get(0));
    }

}
