package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.ModelResponse.MealsModelResponse;

public interface SearchInterface {
    public void getSuccessCategoriesBySearch(MealsModelResponse mealsModelResponse) ;

    public void getSuccessAreaBySearch(MealsModelResponse mealsModelResponse) ;

    public void getSuccessIngredientsBySearch(MealsModelResponse mealsModelResponse) ;

    public void getSuccessNameBySearch(MealsModelResponse mealsModelResponse) ;

    public void getSuccessIdBySearch(MealsModelResponse mealsModelResponse) ;

    public void getSuccessFirstLetterBySearch(MealsModelResponse mealsModelResponse) ;
}
