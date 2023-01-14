package com.example.foodplanner.OnboardingScreen;

import com.example.foodplanner.R;
import java.util.ArrayList;
import java.util.List;

public class OnBoardingScreenArrayListModel {

   public static List<OnBoardingScreenModel> getOnBoardingObjects() {
        ArrayList<OnBoardingScreenModel> object = new ArrayList<OnBoardingScreenModel>();
        object.add(new OnBoardingScreenModel(R.drawable.onborading1,"Breakfast",
                "We also deliver food and\n" + "drinks from the nearest\n" + "supermarket"));
        object.add(new OnBoardingScreenModel(R.drawable.onborading2,"Launch",
                "Test fresh delicious meals\n" + "anytime and anywhere"));
        object.add(new OnBoardingScreenModel(R.drawable.onborading3,"Dinner",
                "Choose your favorite dishes\n" + "frome the nearest restorant or cafe"));
        return object;
    }

}