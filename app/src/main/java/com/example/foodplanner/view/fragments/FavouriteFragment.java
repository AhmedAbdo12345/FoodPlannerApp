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

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.classes.FavPresenter;
import com.example.foodplanner.view.adapters.CategoryAdapter;
import com.example.foodplanner.view.adapters.DayAdapter;
import com.example.foodplanner.view.adapters.FavouriteMealsAdapter;
import com.example.foodplanner.view.adapters.MealsAdapter;
import com.example.foodplanner.view.adapters.PlanMealsAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouriteFragment extends Fragment implements FavouriteMealsAdapter.ListItemClickListener{

    FavPresenter favPresenter;
    FavouriteMealsAdapter favAdapter;
    private static final String TAG = "FavouriteMealsAdapter";
    RecyclerView recyclerViewFavMeals;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favPresenter=new FavPresenter(getContext());
        recyclerViewFavMeals= view.findViewById(R.id.recycler_view_Fav);
        recyclerViewFavMeals.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewFavMeals.setLayoutManager(gridLayoutManager);
        favPresenter.getAllFavMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FavModel>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<FavModel> favModels) {
                        favAdapter = new FavouriteMealsAdapter( favModels , getContext(), FavouriteFragment.this);
                        recyclerViewFavMeals.setAdapter(favAdapter);
                        Log.i(TAG, "List ************** : " + favModels);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    }
                });


    }

    @Override
    public void onClick(int position, FavModel favModel) {
        favPresenter.deleteFav(favModel);

    }
}