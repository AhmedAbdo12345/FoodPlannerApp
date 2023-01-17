package com.example.foodplanner.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.foodplanner.R;
import com.example.foodplanner.view.activities.MainActivity;

public class NavigatorClass {
    public static final String MAINACTIVITY="1";
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
