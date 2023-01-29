package com.example.foodplanner.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPreference {
    private static UserSharedPreference userSharedPreference;
    private SharedPreferences sharedPreferences;

    public static UserSharedPreference getInstance(Context context) {
        if (userSharedPreference == null) {
            userSharedPreference = new UserSharedPreference(context);
        }
        return userSharedPreference;
    }

    private UserSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("sharedPreferences",Context.MODE_PRIVATE);
    }

    public void saveDataInSharedPreference(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.commit();
    }

    public String getDataFromSharedPreference(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }


}
