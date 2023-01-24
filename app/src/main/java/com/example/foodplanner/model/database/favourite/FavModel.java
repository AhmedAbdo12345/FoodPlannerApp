package com.example.foodplanner.model.database.favourite;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavModel {
    @PrimaryKey
    @NonNull String favId;
    private String favName;
    private String favRecipe;
    private String favSteps;
    private String favImg;
    private String favYoutube;

    public FavModel(@NonNull String favId, String favName, String favRecipe, String favSteps, String favImg, String favYoutube) {
        this.favId = favId;
        this.favName = favName;
        this.favRecipe = favRecipe;
        this.favSteps = favSteps;
        this.favImg = favImg;
        this.favYoutube = favYoutube;
    }

    @NonNull
    public String getFavId() {
        return favId;
    }

    public String getFavName() {
        return favName;
    }

    public String getFavRecipe() {
        return favRecipe;
    }

    public String getFavSteps() {
        return favSteps;
    }

    public String getFavImg() {
        return favImg;
    }

    public String getFavYoutube() {
        return favYoutube;
    }
}
