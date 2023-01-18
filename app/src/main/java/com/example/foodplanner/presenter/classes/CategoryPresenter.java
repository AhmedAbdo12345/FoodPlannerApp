package com.example.foodplanner.presenter.classes;

import android.util.Log;

import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.api.MealsApiClient;
import com.example.foodplanner.model.api.MealsApiInterface;
import com.example.foodplanner.presenter.interfaces.CategoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryPresenter {

    CategoryInterface categoryInterface;

    public CategoryPresenter(CategoryInterface categoryInterface) {
        this.categoryInterface = categoryInterface;
    }

   public void getCategories() {
        SingleObserver<CategoryModelResponse> singleObserver=new SingleObserver<CategoryModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull CategoryModelResponse categoryModelResponse) {
                categoryInterface.getSuccessCategoriesFromApi(categoryModelResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
        Single<CategoryModelResponse> single = MealsApiClient.getINSTANE().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);


    }
}