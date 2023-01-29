package com.example.foodplanner.model.database.plan;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.io.Serializable;

@Entity
public class PlanMealsModel extends MealsModel {

    @PrimaryKey
    @NonNull
    String idMeal;
    private String userId;
    private String day;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;

    public PlanMealsModel() {
    }

    public PlanMealsModel(@NonNull String idMeal, String userId, String day, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube) {
        this.idMeal = idMeal;
        this.userId = userId;
        this.day = day;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public String getUserId() {
        return userId;
    }

    public String getDay() {
        return day;
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

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDay(String day) {
        this.day = day;
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
}
