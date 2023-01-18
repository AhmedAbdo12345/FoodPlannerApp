package com.example.foodplanner.presenter.classes;

import com.example.foodplanner.presenter.interfaces.CategoryInterface;

public class CategoryPresenter {

    CategoryInterface categoryInterface;

    public CategoryPresenter(CategoryInterface categoryInterface) {
        this.categoryInterface=categoryInterface;
    }

   /* public void getCategories(){
        MealsApiClient.getINSTANE().getCategories().enqueue(new Callback<CategoryModelResponse>() {
            @Override
            public void onResponse(Call<CategoryModelResponse> call, Response<CategoryModelResponse> response) {
                categoryInterface.getSuccessCategoriesFromApi(response.body().getCategoryModelRequest());

            }

            @Override
            public void onFailure(Call<CategoryModelResponse> call, Throwable t) {
                categoryInterface.getFailureCategoriesFromApi(t.getMessage());

            }
        });



    }*/

}