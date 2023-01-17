package com.example.foodplanner.model.api;

import com.example.foodplanner.model.ModelArrayList.CategoryArrayListModel;
import com.example.foodplanner.model.ModelArrayList.MealsArrayListModel;
import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsApiClient {
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private MealsApiInterface mealsApiInterface;
    private static MealsApiClient INSTANE;

    public MealsApiClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        mealsApiInterface=retrofit.create(MealsApiInterface.class);

    }

    public static MealsApiClient getINSTANE() {
        if (INSTANE == null){
            INSTANE=new MealsApiClient();
        }
        return INSTANE;
    }

    public Call<MealsArrayListModel> getMeals(){
        return mealsApiInterface.getMeals();
    }
    public Call<CategoryArrayListModel> getCategories(){
        return mealsApiInterface.getCategories();
    }
    public Call<MealsArrayListModel> getFilterByCategory(String category){
        return mealsApiInterface.getFilterByCategory(category);
    }
    public Call<MealsArrayListModel> getFilterByName(String name){
        return mealsApiInterface.getFilterByName(name);
    }
}
