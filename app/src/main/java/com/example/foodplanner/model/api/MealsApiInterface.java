package com.example.foodplanner.model.api;

import com.example.foodplanner.model.ModelArrayList.CategoryArrayListModel;
import com.example.foodplanner.model.ModelArrayList.MealsArrayListModel;
import com.example.foodplanner.model.ModelClasses.CategoryModel;
import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsApiInterface {
    @GET("random.php")
    public Call<MealsArrayListModel> getMeals();

    @GET("filter.php")
    public Call<MealsArrayListModel> getFilterByCategory(@Query("c") String category);

    @GET("category")
    public Call<MealsArrayListModel> getFilterByName(@Query("s") String name);

    @GET("category")
    public Call<CategoryArrayListModel> getCategories();
}