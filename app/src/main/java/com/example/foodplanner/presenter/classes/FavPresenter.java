package com.example.foodplanner.presenter.classes;

import android.content.Context;

import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.presenter.interfaces.FavInterface;
import com.example.foodplanner.presenter.repository.FavRepository;
import com.example.foodplanner.presenter.repository.PlanRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavPresenter implements FavInterface {
    FavRepository favRepository;
public FavPresenter(Context context){
    favRepository=new FavRepository(context);
}

    @Override
    public Single<List<FavModel>> getAllFavMeals() {
       return favRepository.getAllFavFromDatabase();
    }

    @Override
    public void insertFav(FavModel model) {
favRepository.insertFav(model);
    }

    @Override
    public void deleteFav(FavModel model) {
favRepository.deleteFav(model);
    }

    @Override
    public void insertAllFav(ArrayList<FavModel> favModelArrayList) {
favRepository.insertAllFavInRoom(favModelArrayList);
    }
}

