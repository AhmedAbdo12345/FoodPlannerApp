package com.example.foodplanner.model.ModelArrayList;

import com.example.foodplanner.model.ModelClasses.CategoryModel;
import com.example.foodplanner.model.ModelClasses.MealsModel;

import java.util.ArrayList;

public class CategoryArrayListModel {
    private ArrayList<CategoryModel> categoryModelArrayList;

    public ArrayList<CategoryModel> getCategoryModelArrayList() {
        return categoryModelArrayList;
    }

    public void setCategoryModelArrayList(ArrayList<CategoryModel> categoryModelArrayList) {
        this.categoryModelArrayList = categoryModelArrayList;
    }
}
