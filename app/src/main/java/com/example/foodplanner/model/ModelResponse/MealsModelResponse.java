package com.example.foodplanner.model.ModelResponse;

import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.util.ArrayList;

public class MealsModelResponse {
    private ArrayList<MealsModel> meals;

    public ArrayList<MealsModel> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<MealsModel> meals) {
        this.meals = meals;
    }
}
