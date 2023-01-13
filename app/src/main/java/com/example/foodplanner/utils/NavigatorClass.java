package com.example.foodplanner.utils;

import android.content.Context;
import android.content.Intent;

import com.example.foodplanner.view.activities.MainActivity;

public class NavigatorClass {
    public static final String MAINACTIVITY="MAIN";

    private Intent intent;

    public static void Navigate(Context context,String distination){
        switch (distination){
            case MAINACTIVITY:
                context.startActivity(new Intent(context, MainActivity.class));
                break;
            default:
                break;
        }
    }

}
