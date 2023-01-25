package com.example.foodplanner.model.database.favourite;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavModel {
    @PrimaryKey
    @NonNull
    private String favId;
    private String favName;
    private String favCateogry;
    private String favImg;


    public FavModel(@NonNull String favId, String favName, String favCateogry, String favImg) {
        this.favId = favId;
        this.favName = favName;
       this.favCateogry = favCateogry;
        this.favImg = favImg;

    }

    @NonNull
    public String getFavId() {
        return favId;
    }

    public String getFavName() {
        return favName;
    }

    public String getFavCateogry() {
        return favCateogry;
    }

    public String getFavImg() {
        return favImg;
    }

}
