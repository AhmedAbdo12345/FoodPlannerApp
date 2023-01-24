package com.example.foodplanner.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DaoMeals {

    @Query("SELECT * FROM PlanMealsModel")
    public Single<List<PlanMealsModel>> loadAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertPlanData(PlanMealsModel model);

    @Delete
    public Completable deletePlanData(PlanMealsModel model);
}
