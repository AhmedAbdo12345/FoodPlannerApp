package com.example.foodplanner.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodplanner.model.ModelClasses.MealsTypeModel;

import java.util.ArrayList;

@Entity
public class PlanMealsModel {

    @PrimaryKey
    @NonNull String userId;
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;
    private ArrayList<String> days;
    private ArrayList<MealsTypeModel> mealsTypes;

    public PlanMealsModel(@NonNull String userId, String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube, ArrayList<String> days, ArrayList<MealsTypeModel> mealsTypes) {
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

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    public void setMealsTypes(ArrayList<MealsTypeModel> mealsTypes) {
        this.mealsTypes = mealsTypes;
    }

    @NonNull
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
