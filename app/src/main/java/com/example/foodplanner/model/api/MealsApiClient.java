package com.example.foodplanner.model.api;

import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsApiClient {
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private MealsApiInterface mealsApiInterface;
    private static MealsApiClient INSTANE;



    public MealsApiClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build();


        mealsApiInterface=retrofit.create(MealsApiInterface.class);

    }

    public static MealsApiClient getINSTANE() {
        if (INSTANE == null){
            INSTANE=new MealsApiClient();
        }
        return INSTANE;
    }

    public Single<MealsModelResponse> getSingleRandomMeal(){
        return mealsApiInterface.getSingleRandomMeal();
    }
    public Single<CategoryModelResponse> getCategories(){
        return mealsApiInterface.getCategories();
    }

    public Single<MealsModelResponse> getFilterByArea(String area){
        return mealsApiInterface.getFilterByArea(area);
    }

    public Single<MealsModelResponse> getFilterByCategory(String category){
        return mealsApiInterface.getFilterByCategory(category);
    }

    public Single<MealsModelResponse> getFilterByIngredient(String ingredient){
        return mealsApiInterface.getFilterByIngredient(ingredient);
    }

    public Single<MealsModelResponse> getSearchMealsByName(String name){
        return mealsApiInterface.getSearchMealsByName(name);
    }

    public Single<MealsModelResponse> getSearchMealsByID(String id){
        return mealsApiInterface.getSearchMealsByID(id);
    }

    public Single<MealsModelResponse> getSearchMealsByFirstLetter(String firstLetter){
        return mealsApiInterface.getSearchMealsByFirstLetter(firstLetter);
    }


}
