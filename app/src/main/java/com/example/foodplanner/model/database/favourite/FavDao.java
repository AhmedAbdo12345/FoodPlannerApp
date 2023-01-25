package com.example.foodplanner.model.database.favourite;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface FavDao {
    @Query("SELECT * FROM FavModel")
    public Single<List<FavModel>> loadAllFavs();

    @Query("SELECT * FROM FavModel where favName LIKE :name")
    public Single<FavModel> getFav(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable inserFavtMeal (FavModel favModel);

    @Delete
   public Completable deleteFavMeal(FavModel favModel);

}
