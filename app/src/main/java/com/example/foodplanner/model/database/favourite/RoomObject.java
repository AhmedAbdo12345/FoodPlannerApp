package com.example.foodplanner.model.database.favourite;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.database.plan.DaoMeals;
import com.example.foodplanner.model.database.plan.DatabaseMeals;
import com.example.foodplanner.model.database.plan.PlanMealsModel;

@Database(entities = {FavModel.class}, version = 1, exportSchema = false)
public abstract class RoomObject extends RoomDatabase {
    private static final String DATABASE_NAME = "MealsFav";
    private static RoomObject roomObject = null;


    public synchronized static RoomObject getInstance(Context context){
        if(roomObject == null){
            roomObject = Room.databaseBuilder(context.getApplicationContext(),
                            RoomObject.class, RoomObject.DATABASE_NAME)
                    .build();
        }
        return roomObject;
    }

    public abstract FavDao taskDao();

}
