package com.example.foodplanner.model.ModelClasses;

import com.example.foodplanner.model.database.plan.PlanMealsModel;

import java.util.List;

public class DisplayPlanModel {
    private String nameDay;
    private List<PlanMealsModel> listItemPlan;

    public DisplayPlanModel(String nameDay, List<PlanMealsModel> childItemList) {
        this.nameDay = nameDay;
        this.listItemPlan = childItemList;
    }

    public String getnameDay() {
        return nameDay;
    }

    public void setnameDay(String nameDay) {
        this.nameDay = nameDay;
    }

    public List<PlanMealsModel> getListItemPlan() {
        return listItemPlan;
    }

    public void setListItemPlan(List<PlanMealsModel> listItemPlan) {
        this.listItemPlan = listItemPlan;
    }
}
