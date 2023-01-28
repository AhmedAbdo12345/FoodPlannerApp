package com.example.foodplanner.model.database.favourite;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.database.plan.DaoMeals;
import com.example.foodplanner.model.database.plan.PlanMealsModel;

@Database(entities = {FavModel.class}, version = 1, exportSchema = false)
public abstract class DatabaseFavMeals extends RoomDatabase {
    private static final String LOG_TAG = "DataBaseStatus";
    private static final String DATABASE_NAME = "MealsFav";
    private static com.example.foodplanner.model.database.favourite.DatabaseFavMeals instance=null;

    public synchronized static com.example.foodplanner.model.database.favourite.DatabaseFavMeals getInstance(Context context) {
        if (instance == null) {
            Log.d(LOG_TAG, "Creating new database instance");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            com.example.foodplanner.model.database.favourite.DatabaseFavMeals.class, com.example.foodplanner.model.database.favourite.DatabaseFavMeals.DATABASE_NAME)
                    .build();

        }
        Log.d(LOG_TAG, "Getting the database instance");
        return instance;
    }

    public abstract FavDao taskDao();


}
