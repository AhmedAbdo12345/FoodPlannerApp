package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    ImageView imgMeal;
    TextView tvTitleMeal, tvCategoryMeal, tvInstructions,tvArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgMeal = view.findViewById(R.id.img_meal_details);
        tvTitleMeal = view.findViewById(R.id.tv_title_details);
        tvCategoryMeal = view.findViewById(R.id.tv_meal_category_details);
        tvInstructions = view.findViewById(R.id.tv_instructions_details);
        tvArea=view.findViewById(R.id.tv_area_details);
        MealsModel model = DetailsFragmentArgs.fromBundle(getArguments()).getMeal();
        tvTitleMeal.setText(model.getStrMeal());
        tvCategoryMeal.setText(model.getStrCategory());
        tvInstructions.setText(model.getStrInstructions());
        tvArea.setText(model.getStrArea());

        if(model.getStrMealThumb() !=null) {
            Picasso.get().load(model.getStrMealThumb()).into(imgMeal);
        }


    }
}