package com.example.foodplanner.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.foodplanner.R;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController= Navigation.findNavController(this,R.id.nav_host_main_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() ||super.onSupportNavigateUp();
    }
}