package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.example.foodplanner.presenter.classes.MealsPresenter;
import com.example.foodplanner.presenter.interfaces.MealstInterface;

import java.util.List;


public class HomeFragment extends Fragment implements MealstInterface {
    TextView textView;
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
        homeFragmentPresenter.getListOfRandomMeals();
    }



    @Override
    public void getFailureMealsFromApi(String message) {

    }

    @Override
    public void getSuccessMealsFromApi(List<MealsModelResponse> mealsModelRequestList) {
        if (mealsModelRequestList.size() >0){
            Log.i("zxc", mealsModelRequestList.toString());
            for (int i=0;i<mealsModelRequestList.size();i++){
              //  Log.i("zxc",String.valueOf( mealsModelRequestList.get(0).getMealsModelRequest().size()));
//                Log.i("zxcv", mealsModelRequestList.get(0).getMealsModelRequest().get(0).getStrCategory());

            }
           // textView.setText(mealsModelRequestList.get(0).getMealsModelRequest().get(0).getStrCategory());
        }
    }


}