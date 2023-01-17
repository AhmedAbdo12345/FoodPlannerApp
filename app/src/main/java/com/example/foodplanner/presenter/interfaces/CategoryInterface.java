package com.example.foodplanner.presenter.interfaces;

import com.example.foodplanner.model.ModelArrayList.CategoryArrayListModel;
import com.example.foodplanner.model.ModelClasses.CategoryModel;
import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.util.ArrayList;

public class CategoryInterface {
    public void getSuccessCategoriesFromApi(ArrayList<CategoryModel> categoryArrayListModels) {
    }

    public void getFailureCategoriesFromApi(String message) {
    }
}
