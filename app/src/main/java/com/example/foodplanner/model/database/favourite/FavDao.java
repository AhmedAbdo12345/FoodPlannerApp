package com.example.foodplanner.model.database.favourite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface FavDao {
    @Query("SELECT * FROM FavModel")
    Single<List<FavModel>> getAllFav();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertFavData(FavModel model);

    @Delete
    public Completable deleteFavMeal(FavModel model);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlFav(ArrayList<FavModel> favModelArrayList);

}
