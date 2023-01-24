package com.example.foodplanner.model.ModelResponse;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_favs")
public class FavModel {
    @PrimaryKey
    @ColumnInfo(name = "favId")
    @NonNull
    private int favId;

    @ColumnInfo(name = "favName")
    private String favName;

    @ColumnInfo(name = "favRecipe")
    private String favRecipe;

    @ColumnInfo(name = "favSteps")
    private String favSteps;


    @ColumnInfo(name = "favImg")
    private String favImg;

    @ColumnInfo(name = "favYoutube")
    private String favYoutube;


    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
        this.favId = favId;
    }

    public String getFavName() {
        return favName;
    }

    public void setFavName(String favName) {
        this.favName = favName;
    }

    public String getFavRecipe() {
        return favRecipe;
    }

    public void setFavRecipe(String favRecipe) {
        this.favRecipe = favRecipe;
    }

    public String getFavSteps() {
        return favSteps;
    }

    public void setFavSteps(String favSteps) {
        this.favSteps = favSteps;
    }

    public String getFavImg() {
        return favImg;
    }

    public void setFavImg(String favImg) {
        this.favImg = favImg;
    }

    public String getFavYoutube() {
        return favYoutube;
    }

    public void setFavYoutube(String favYoutube) {
        this.favYoutube = favYoutube;
    }


}
