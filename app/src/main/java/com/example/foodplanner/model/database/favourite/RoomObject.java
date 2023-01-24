package com.example.foodplanner.model.database.favourite;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.database.plan.DaoMeals;

public abstract class RoomObject extends RoomDatabase {

    private static Builder<RoomObject> roomObject = null;
    public abstract FavDao favDao();
    public abstract DaoMeals planDao();

    public static synchronized Builder<RoomObject> getInstance(Context context){
        if(roomObject == null){
            roomObject = Room.databaseBuilder(context.getApplicationContext(), RoomObject.class, "databaseMeals");
        }
        return roomObject;
    }

}
