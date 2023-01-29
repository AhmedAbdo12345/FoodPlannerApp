package com.example.foodplanner.model.database.plan;

import androidx.lifecycle.LiveData;
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
public interface DaoMeals {
    //-------------------------------------
    @Query("SELECT * FROM PlanMealsModel")
    public Single<List<PlanMealsModel>> loadAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertPlanData(PlanMealsModel model);

    @Delete
    public Completable deletePlanMeal(PlanMealsModel model);

    @Query("DELETE FROM PlanMealsModel")
    public Completable deletePlanTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPlan(ArrayList<PlanMealsModel> planMealsModelArrayList);


    @Query("SELECT * FROM PlanMealsModel WHERE day =:day" )
    LiveData<List<PlanMealsModel>> getDataForDay(String day);
}
