package com.example.foodplanner.OnboardingScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodplanner.view.activities.AuthActivity;
import com.example.foodplanner.view.activities.HomeActivity;
import com.example.foodplanner.view.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null && currentUser.isEmailVerified()) {
                //user signed in
                    startActivity(new Intent(this, HomeActivity.class));
                    this.finish();


            }else {
                intent =new Intent(this, AuthActivity.class);
                startActivity(intent);

            }
       } else {

        intent =new Intent(this,OnboardingScreenActivity.class);
            startActivity(intent);

        }


    }



}
