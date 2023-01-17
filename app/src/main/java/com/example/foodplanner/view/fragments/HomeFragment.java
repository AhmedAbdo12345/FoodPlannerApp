package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.presenter.classes.MealsPresenter;
import com.example.foodplanner.presenter.interfaces.MealstInterface;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements MealstInterface {

    MealsPresenter homeFragmentPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeFragmentPresenter=new MealsPresenter(this);
        homeFragmentPresenter.getMeals();
    }

    @Override
    public void getSuccessMealsFromApi(ArrayList<MealsModel> body) {

    }

    @Override
    public void getFailureMealsFromApi(String message) {

    }
}