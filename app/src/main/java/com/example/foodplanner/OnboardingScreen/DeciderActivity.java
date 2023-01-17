package com.example.foodplanner.OnboardingScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.foodplanner.view.activities.AuthActivity;
import com.example.foodplanner.view.activities.HomeActivity;
import com.example.foodplanner.view.activities.MainActivity;

public class DeciderActivity extends AppCompatActivity {
    Intent intent;
    public  static String MYPREFERENCES="androidx.appcompat.app.SHARED_PREF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_decider);
        prepareProperties();
        finish();
    }

    private void prepareProperties() {
        SharedPreferences Shared=getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        boolean seen=Shared.getBoolean("seen",false);
        // Log.d("seen",String.valueOf(seen));
        if (seen){
           intent =new Intent(this, AuthActivity.class);
       } else {

        intent =new Intent(this,OnboardingScreenActivity.class);
         }
        startActivity(intent);


    }



}
