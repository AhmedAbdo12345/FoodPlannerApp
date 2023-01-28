package com.example.foodplanner.model.database.favourite;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavModel {

    @PrimaryKey
    @NonNull
    String idMeal;
    private String userId;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;

    public FavModel() {
    }

    public FavModel(@NonNull String idMeal, String userId,  String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube) {
        this.idMeal = idMeal;
        this.userId = userId;

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
