package com.example.foodplanner.model.ModelClasses;

import java.util.ArrayList;

public class PlanModel {
    private String firebaseID;
    private String userId;
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;
    private ArrayList<String> days;
    private ArrayList<MealsTypeModel> mealsTypes;

    public PlanModel(String firebaseID, String userId, String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube, ArrayList<String> days, ArrayList<MealsTypeModel> mealsTypes) {
        this.firebaseID = firebaseID;
        this.userId = userId;
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.days = days;
        this.mealsTypes = mealsTypes;
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public String getUserId() {
        return userId;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public ArrayList<MealsTypeModel> getMealsTypes() {
        return mealsTypes;
    }
}
