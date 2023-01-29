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
import android.view.View;
import android.widget.Toast;

import com.example.foodplanner.model.database.plan.DatabaseMeals;
import com.example.foodplanner.presenter.classes.FavPresenter;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.example.foodplanner.utils.NetworkConnection;
import com.example.foodplanner.utils.UserSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.foodplanner.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    NavController navController;
    View fav, plan,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeNav, R.id.favNav, R.id.plansNav)
                .build();

        fav = findViewById(R.id.favNav);
        plan = findViewById(R.id.plansNav);
       // profile=findViewById(R.id.profileNav);
        String user = UserSharedPreference.getInstance(this).getDataFromSharedPreference("user");
        if (user.equals("Guest") || (!NetworkConnection.isNetworkAvailable(this))) {
            fav.setVisibility(View.GONE);
            plan.setVisibility(View.GONE);
         //   profile.setVisibility(View.GONE);
        } else {
            fav.setVisibility(View.VISIBLE);
            plan.setVisibility(View.VISIBLE);
          //  profile.setVisibility(View.VISIBLE);

        }
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

