package com.example.foodplanner.presenter.interfaces;

public interface DaySelectedInterface {
    void onLunchSelected(String day,boolean isChecked);
    void onBreakfastSelected(String day,boolean isChecked);
    void onDinnerSelected(String day,boolean isChecked);
    void onDaySelected(String nameDay,boolean isChecked);


}
