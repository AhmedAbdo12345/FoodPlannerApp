package com.example.foodplanner.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.foodplanner.model.database.plan.DatabaseMeals;
import com.example.foodplanner.presenter.classes.FavPresenter;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.foodplanner.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeNav, R.id.favNav, R.id.profileNav, R.id.plansNav)
                .build();
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            //-------------To Delete Plan Table -----------------------
           PlanPresenter planPresenter = new PlanPresenter(HomeActivity.this);
            planPresenter.deletePlanTable();
            //------------------------------------
            //-------------To Delete Fav Table -----------------------
            FavPresenter favPresenter = new FavPresenter(HomeActivity.this);
            favPresenter.deleteFavTable();
            //------------------------------------


            FirebaseAuth.getInstance().signOut();

            startActivity(new Intent(HomeActivity.this, AuthActivity.class));
            HomeActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}

