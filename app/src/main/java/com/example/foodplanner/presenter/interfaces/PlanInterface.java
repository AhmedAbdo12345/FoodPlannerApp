package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.database.plan.PlanMealsModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PlanInterface {

    public Single<List<PlanMealsModel>> getAllPlanMeals();
    public void insertPlan(PlanMealsModel model);
    public void deletePlan(PlanMealsModel model);

}
