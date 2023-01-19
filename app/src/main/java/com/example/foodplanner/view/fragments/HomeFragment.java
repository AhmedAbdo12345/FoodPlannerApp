package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.example.foodplanner.presenter.classes.CategoryPresenter;
import com.example.foodplanner.presenter.classes.MealsPresenter;
import com.example.foodplanner.presenter.interfaces.CategoryInterface;
import com.example.foodplanner.presenter.interfaces.MealstInterface;
import com.example.foodplanner.utils.ConstantsClass;
import com.example.foodplanner.view.adapters.CategoryAdapter;
import com.example.foodplanner.view.adapters.MealsAdapter;

import java.util.List;


public class HomeFragment extends Fragment implements MealstInterface , CategoryInterface, MealsAdapter.ListItemClickListener
,CategoryAdapter.ListItemClickListener{
    MealsPresenter mealsPresenter;
    CategoryPresenter categoryPresenter;

    RecyclerView recyclerView_random,recyclerView_category;
    MealsAdapter mealsAdapter;
    CategoryAdapter categoryAdapter;
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
        recyclerView_random = view.findViewById(R.id.rv_randomMeal);
        recyclerView_category=view.findViewById(R.id.rv_categories);

        mealsPresenter=new MealsPresenter(this);
        mealsPresenter.getListOfRandomMeals();

        categoryPresenter=new CategoryPresenter(this);
        categoryPresenter.getCategories();
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
            recyclerView_random.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
            gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView_random.setLayoutManager(gridLayoutManager);

            mealsAdapter = new MealsAdapter(mealsModelRequestList,getContext(),HomeFragment.this);
            recyclerView_random.setAdapter(mealsAdapter);
        }
    }


    @Override
    public void getSuccessCategoriesFromApi(CategoryModelResponse categoryArrayListModels) {
        recyclerView_category.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_category.setLayoutManager(gridLayoutManager);

        categoryAdapter = new CategoryAdapter(categoryArrayListModels,getContext(),HomeFragment.this);
        recyclerView_category.setAdapter(categoryAdapter);
    }

    @Override
    public void getFailureCategoriesFromApi(String message) {

    }

    @Override
    public void onClickCategory(int position) {

    }


    @Override
    public void onClickMeals(int position, List<MealsModelResponse> mealsModelResponses) {
        MealsModel mealsModel=mealsModelResponses.get(position).getMeals().get(0);
        HomeFragmentDirections.ActionHomeNavToDetailsFragment action = HomeFragmentDirections.actionHomeNavToDetailsFragment(mealsModel);
        Navigation.findNavController(getView()).navigate(action);
    }
}