package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;

import io.reactivex.rxjava3.annotations.NonNull;

public interface CategoryInterface {
    public void getSuccessCategoriesFromApi(CategoryModelResponse categoryArrayListModels) ;

    public void getFailureCategoriesFromApi(String message);
}
