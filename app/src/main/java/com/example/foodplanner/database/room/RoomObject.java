package com.example.foodplanner.database.room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.database.favourite.FavDao;
import com.example.foodplanner.database.plan.PlanDao;

public abstract class RoomObject extends RoomDatabase {

    private static Builder<RoomObject> roomObject = null;
    public abstract FavDao favDao();
    public abstract PlanDao planDao();

    public static synchronized Builder<RoomObject> getInstance(Context context){
        if(roomObject == null){
            roomObject = Room.databaseBuilder(context.getApplicationContext(), RoomObject.class, "databaseMeals");
        }
        return roomObject;
    }

}
