package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.util.ArrayList;

public interface MealstInterface {
    void getSuccessMealsFromApi(ArrayList<MealsModel> body);
    void getFailureMealsFromApi(String  message);
}
