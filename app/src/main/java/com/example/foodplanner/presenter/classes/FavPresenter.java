package com.example.foodplanner.presenter.classes;

import android.content.Context;

import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.presenter.interfaces.FavInterface;
import com.example.foodplanner.presenter.repository.FavRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavPresenter implements FavInterface {

    FavRepository favRepository;
    public FavPresenter(Context context){
        favRepository=new FavRepository(context);
    }

    @Override
    public Single<List<FavModel>> getAllFavMeals() {
        return favRepository.getAllFavsFromDatabase();
    }

    @Override
    public void insertFav(FavModel favModel) {
        favRepository.insertFav(favModel);

    }

    @Override
    public void deleteFav(FavModel favModel) {
        favRepository.deleteFav(favModel);
    }
}
