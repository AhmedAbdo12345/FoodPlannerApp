package com.example.foodplanner.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.OnClick;
import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.presenter.classes.CategoryPresenter;
import com.example.foodplanner.presenter.classes.SearchPresenter;
import com.example.foodplanner.presenter.interfaces.CategoryInterface;
import com.example.foodplanner.view.adapters.FavouriteMealsAdapter;
import com.example.foodplanner.view.adapters.MealByCategoryAdapter;

import java.util.List;


public class CategoryFragment extends Fragment implements MealByCategoryAdapter.ClickListener, CategoryInterface {


    RecyclerView recyclerView;
    ImageView imageCategory;
    ImageView imageCategoryBg;
    TextView textCategory;
    AlertDialog.Builder descDialog;
    CategoryPresenter categoryPresenter;
    MealByCategoryAdapter adapter;
    SearchPresenter searchPresenter;

    public CategoryFragment() { }


    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String nameCategory = CategoryFragmentArgs.fromBundle(getArguments()).getNameCategory();

           searchPresenter = new SearchPresenter(this, false);
           searchPresenter.searchByCategory(nameCategory);

            recyclerView = view.findViewById(R.id.recyclerViewCategory);
            //imageCategoryBg = view.findViewById(R.id.imageCategoryBg);
            //textCategory = view.findViewById(R.id.textCategory);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        }



    @Override
    public void onClick(View view, int position) {
    }

    @Override
    public void getSuccessCategoriesFromApi(CategoryModelResponse categoryArrayListModels) {

    }

    @Override
    public void getSuccessCategoriesBySearch(MealsModelResponse mealsModelResponse) {
        adapter = new MealByCategoryAdapter(getContext(), mealsModelResponse);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getFailureCategoriesFromApi(String message) {

    }
}