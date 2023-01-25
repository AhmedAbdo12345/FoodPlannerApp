package com.example.foodplanner.model.ModelClasses;

import java.util.ArrayList;
import java.util.Map;

public class PModel {
    private String day;
    private Map<String , PlanModel> mapMeal;

    public PModel(String day, Map<String, PlanModel > mapMeal) {
        this.day = day;
        this.mapMeal = mapMeal;
    }

    public String getDay() {
        return day;
    }

    public Map<String, PlanModel > getMapMeal() {
        return mapMeal;
    }
}
