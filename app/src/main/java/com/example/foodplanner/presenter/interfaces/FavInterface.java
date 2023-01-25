package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.database.favourite.FavModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FavInterface {
    public Single<List<FavModel>> getAllFavMeals();
    public void insertFav(FavModel model);
    public void deleteFav(FavModel model);
}
