package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.ModelResponse.MealsModelResponse;

import java.util.List;

public interface MealstInterface {
    void getFailureMealsFromApi(String  message);
    void getSuccessMealsFromApi(List<MealsModelResponse> mealsModelRequestList);

}
