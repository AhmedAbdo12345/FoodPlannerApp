package com.example.foodplanner.model.ModelResponse;

import com.example.foodplanner.model.ModelClasses.CategoryModel;

import java.util.ArrayList;

public class CategoryModelResponse {
    private ArrayList<CategoryModel> categories;

    public ArrayList<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }
}
