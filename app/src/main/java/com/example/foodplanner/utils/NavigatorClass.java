package com.example.foodplanner.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.foodplanner.R;
import com.example.foodplanner.view.activities.MainActivity;

public class NavigatorClass {
    public static final String MAINACTIVITY="1";
    public static final String GO_FROM_SIGNUP_TO_LOGIN="2";
    private Intent intent;

    public static void Navigate(Context context,String distination,View view){
        switch (distination){
            case MAINACTIVITY:
                context.startActivity(new Intent(context, MainActivity.class));
                break;
            case GO_FROM_SIGNUP_TO_LOGIN:
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
                break;
            default:
                break;
        }
    }



}
