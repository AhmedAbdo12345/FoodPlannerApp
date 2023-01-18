package com.example.foodplanner.model.api;

import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsApiInterface {
    @GET("random.php")
    public Single<MealsModelResponse> getSingleRandomMeal();

    @GET("filter.php")
    public Single<MealsModelResponse> getFilterByCategory(@Query("c") String category);

    @GET("filter.php")
    public Single<MealsModelResponse> getFilterByIngredient(@Query("i") String category);

    @GET("filter.php")
    public Single<MealsModelResponse> getFilterByArea(@Query("a") String area);




    @GET("search.php")
    public Single<MealsModelResponse> getSearchMealsByName(@Query("s") String name);


    @GET("search.php")
    public Single<MealsModelResponse> getSearchMealsByFirstLetter(@Query("f") String first);


    @GET("search.php")
    public Single<MealsModelResponse> getSearchMealsByID(@Query("i") String id);


    @GET("categories.php")
    public Single<CategoryModelResponse> getCategories();
}