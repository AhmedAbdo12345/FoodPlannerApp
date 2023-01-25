package com.example.foodplanner.presenter.repository;

import android.content.Context;
import android.widget.Toast;

import com.example.foodplanner.model.database.favourite.FavDao;
import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.model.database.favourite.RoomObject;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavRepository {

    private Context context;
    private FavDao favDao;
    private Single<List<FavModel>> listLiveData;


    public FavRepository(Context context){
        this.context=context;
        RoomObject db = RoomObject.getInstance(context);
        favDao = db.taskDao();
        listLiveData = favDao.loadAllFavs();
    }
    public Single<List<FavModel>> getAllFavsFromDatabase() {
        return listLiveData;
    }

    public void insertFav(FavModel model) {
        favDao.inserFavtMeal(model).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

                Toast.makeText(context, "Add Successfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
    public void deleteFav(FavModel model) {
        favDao.deleteFavMeal(model).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d){

            }
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
