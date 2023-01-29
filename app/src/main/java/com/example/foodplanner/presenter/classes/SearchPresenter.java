package com.example.foodplanner.presenter.classes;

import android.util.Log;

import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.example.foodplanner.model.api.MealsApiClient;
import com.example.foodplanner.presenter.interfaces.CategoryInterface;
import com.example.foodplanner.presenter.interfaces.SearchInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
SearchInterface searchInterface;
CategoryInterface categoryInterface;
boolean check = true;

    public SearchPresenter(SearchInterface searchInterface) {
        this.searchInterface=searchInterface;
    }
    public SearchPresenter(CategoryInterface categoryInterface, boolean check) {
        this.categoryInterface= categoryInterface ;
        this.check = check;

    }

    public void searchByCategory(String category){
        SingleObserver<MealsModelResponse> singleObserver=new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsModelResponse) {
                Log.i("zxc", "onSuccess: "+mealsModelResponse);
                  if(check == true){
                      searchInterface.getSuccessCategoriesBySearch(mealsModelResponse);
                  }
                  else {
                      categoryInterface.getSuccessCategoriesBySearch(mealsModelResponse);
                  }

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single = MealsApiClient.getINSTANE().getFilterByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);

    }
    public void searchByArea(String area){
        SingleObserver<MealsModelResponse> singleObserver=new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsModelResponse) {
                Log.i("zxc", "onSuccess: "+mealsModelResponse);
                searchInterface.getSuccessAreaBySearch(mealsModelResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single = MealsApiClient.getINSTANE().getFilterByArea(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);

    }
    public void searchByIngredients(String ingredients){
        SingleObserver<MealsModelResponse> singleObserver=new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsModelResponse) {
                searchInterface.getSuccessIngredientsBySearch(mealsModelResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single = MealsApiClient.getINSTANE().getFilterByIngredient(ingredients)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);

    }

    public void searchByName(String name){
        SingleObserver<MealsModelResponse> singleObserver=new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsModelResponse) {
                searchInterface.getSuccessNameBySearch(mealsModelResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single = MealsApiClient.getINSTANE().getSearchMealsByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);

    }


    public void searchByID(String id){
        SingleObserver<MealsModelResponse> singleObserver=new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsModelResponse) {
                searchInterface.getSuccessIdBySearch(mealsModelResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single = MealsApiClient.getINSTANE().getSearchMealsByID(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);

    }


    public void searchByFirstLetter(String firstLetter){
        SingleObserver<MealsModelResponse> singleObserver=new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsModelResponse) {
                searchInterface.getSuccessFirstLetterBySearch(mealsModelResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single = MealsApiClient.getINSTANE().getSearchMealsByFirstLetter(firstLetter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        single.subscribe(singleObserver);

    }

}
