package com.example.foodplanner.presenter.classes;

import com.example.foodplanner.model.ModelArrayList.CategoryArrayListModel;
import com.example.foodplanner.model.ModelArrayList.MealsArrayListModel;
import com.example.foodplanner.model.api.MealsApiClient;
import com.example.foodplanner.presenter.interfaces.CategoryInterface;
import com.example.foodplanner.presenter.interfaces.MealstInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    CategoryInterface categoryInterface;

    public CategoryPresenter(CategoryInterface categoryInterface) {
        this.categoryInterface=categoryInterface;
    }

    public void getCategories(){
        MealsApiClient.getINSTANE().getCategories().enqueue(new Callback<CategoryArrayListModel>() {
            @Override
            public void onResponse(Call<CategoryArrayListModel> call, Response<CategoryArrayListModel> response) {
                categoryInterface.getSuccessCategoriesFromApi(response.body().getCategoryModelArrayList());

            }

            @Override
            public void onFailure(Call<CategoryArrayListModel> call, Throwable t) {
                categoryInterface.getFailureCategoriesFromApi(t.getMessage());

            }
        });



    }

}