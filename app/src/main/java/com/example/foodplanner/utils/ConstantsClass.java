package com.example.foodplanner.utils;

public class ConstantsClass {
    public static final String WELCOME="WELCOME";
    public static final String MEALS="Meals";
    public static final String CATEGORY="Category";
    public static final String Area="Area";
    public static final String Ingredients="Ingredients";

    public static  String USERNAME;
    public static  String EMAIL;

    public static String getEMAIL() {
        return EMAIL;
    }

    public static void setEMAIL(String EMAIL) {
        ConstantsClass.EMAIL = EMAIL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        ConstantsClass.USERNAME = USERNAME;
    }
}
