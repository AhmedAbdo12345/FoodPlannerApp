package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.presenter.classes.FavPresenter;
import com.example.foodplanner.view.adapters.FavAdapter;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

//call by object of presenter


public class FavouriteFragment extends Fragment {

    RecyclerView recyclerView;
    FavAdapter favAdapter;
    FavPresenter favPresenter;
    Single<List<FavModel>> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favPresenter = new FavPresenter(getContext());
        list = favPresenter.getAllFavMeals();
        recyclerView = recyclerView.findViewById(R.id.favMeals);
        favAdapter = new FavAdapter(this, list);
        recyclerView.setAdapter(favAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);

    }
}