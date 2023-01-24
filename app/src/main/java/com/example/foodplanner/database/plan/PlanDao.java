package com.example.foodplanner.database.plan;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.ModelResponse.PlanModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface PlanDao {
    @Query("select * from meal_plans")
    Single<List<PlanModel>> getPlans();

    @Query("select * from meal_plans where pName LIKE :name")
    Single<PlanModel> getMyPlan(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal (PlanModel planModel);

    @Delete
    Completable deleteMeal(PlanModel planModel);

}
