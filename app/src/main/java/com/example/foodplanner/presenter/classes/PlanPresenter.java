package com.example.foodplanner.presenter.classes;

import android.content.Context;

import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.interfaces.PlanInterface;
import com.example.foodplanner.presenter.repository.PlanRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class PlanPresenter implements PlanInterface{

PlanRepository planRepository;
    public PlanPresenter(Context context){
        planRepository=new PlanRepository(context);
    }

   /* private Single<List<PlanMealsModel>> getAllPlan(){
       return planRepository.getAllPlansFromDatabase();
    }
    private void insertNewPlan(PlanMealsModel model){
        planRepository.insert(model);
    }
    private void deleteNewPlan(PlanMealsModel model){
        planRepository.deletePlan(model);
    }*/



    @Override
    public Single<List<PlanMealsModel>> getAllPlanMeals() {
        return planRepository.getAllPlansFromDatabase();
    }

    @Override
    public void insertPlan(PlanMealsModel model) {
        planRepository.insert(model);

    }

    @Override
    public void deletePlan(PlanMealsModel model) {
        planRepository.deletePlan(model);

    }
}
