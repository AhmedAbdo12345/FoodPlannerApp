package com.example.foodplanner.presenter.classes;

import com.example.foodplanner.model.ModelArrayList.MealsArrayListModel;
import com.example.foodplanner.model.api.MealsApiClient;
import com.example.foodplanner.presenter.interfaces.MealstInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsPresenter {

    MealstInterface homeFragmentInterface;

    public MealsPresenter(MealstInterface homeFragmentInterface) {
        this.homeFragmentInterface=homeFragmentInterface;
    }

    public void getMeals(){
        MealsApiClient.getINSTANE().getMeals().enqueue(new Callback<MealsArrayListModel>() {
            @Override
            public void onResponse(Call<MealsArrayListModel> call, Response<MealsArrayListModel> response) {
                homeFragmentInterface.getSuccessMealsFromApi(response.body().getMealsModelArrayList());
            }

            @Override
            public void onFailure(Call<MealsArrayListModel> call, Throwable t) {
                homeFragmentInterface.getFailureMealsFromApi(t.getMessage());
            }
        });


    }

}
