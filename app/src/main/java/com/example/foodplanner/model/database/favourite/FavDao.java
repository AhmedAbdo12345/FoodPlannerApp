package com.example.foodplanner.model.database.favourite;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface FavDao {
    @Query("select * from favmodel")
    Single<List<FavModel>> getFavs();

    @Query("select * from favmodel where favName LIKE :name")
    Single<FavModel> getFav(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal (FavModel favModel);

    @Delete
    Completable deleteMeal(FavModel favModel);

}
