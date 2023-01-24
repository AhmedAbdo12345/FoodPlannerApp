package com.example.foodplanner.model.database;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PlanMealsModel.class}, version = 1, exportSchema = false)
public abstract class DatabaseMeals extends RoomDatabase {
    private static final String LOG_TAG = "DataBaseStatus";
    private static final String DATABASE_NAME = "MealsPlan";
    private static DatabaseMeals instance=null;

    public synchronized static DatabaseMeals getInstance(Context context) {
        if (instance == null) {
            Log.d(LOG_TAG, "Creating new database instance");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseMeals.class, DatabaseMeals.DATABASE_NAME)
                    .build();

        }
        Log.d(LOG_TAG, "Getting the database instance");
        return instance;
    }

    public abstract DaoMeals taskDao();


}
