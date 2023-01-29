package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;

public interface CategoryInterface {
    public void getSuccessCategoriesFromApi(CategoryModelResponse categoryArrayListModels) ;
    public void getSuccessCategoriesBySearch(MealsModelResponse mealsModelResponse) ;
    public void getFailureCategoriesFromApi(String message);
}
