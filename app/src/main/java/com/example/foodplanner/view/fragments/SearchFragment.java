package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.example.foodplanner.model.api.MealsApiInterface;
import com.example.foodplanner.presenter.classes.SearchPresenter;
import com.example.foodplanner.presenter.interfaces.SearchInterface;
import com.example.foodplanner.utils.ConstantsClass;
import com.example.foodplanner.view.adapters.SearchAdapter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchInterface ,SearchAdapter.ListItemClickListener {
EditText editTextSearch;
SearchPresenter searchPresenter;
RecyclerView recyclerView_search_category;
SearchAdapter searchAdapter;
    MealsModel mealsModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsModel=new MealsModel();
        recyclerView_search_category=view.findViewById(R.id.recycler_view_Search);
        searchPresenter=new SearchPresenter(this);
        String searchBy = SearchFragmentArgs.fromBundle(getArguments()).getSeachBy();
        editTextSearch=view.findViewById(R.id.edt_search_id);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchBy.equals(ConstantsClass.CATEGORY)){
                    searchPresenter.searchByCategory(s.toString());
                }
                if(searchBy.equals(ConstantsClass.Area)){
                    searchPresenter.searchByArea(s.toString());
                }
                if(searchBy.equals(ConstantsClass.Ingredients)){
                    searchPresenter.searchByIngredients(s.toString());
                }
                if(searchBy.equals(ConstantsClass.Name)){
                    searchPresenter.searchByName(s.toString());
                }
                if(searchBy.equals(ConstantsClass.Id)){
                    searchPresenter.searchByID(s.toString());
                }
                if(searchBy.equals(ConstantsClass.FirstLetter)){
                    searchPresenter.searchByFirstLetter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        recyclerView_search_category.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView_search_category.setLayoutManager(gridLayoutManager);


    }

    @Override
    public void getSuccessCategoriesBySearch(MealsModelResponse mealsModelResponse) {
        searchAdapter = new SearchAdapter(mealsModelResponse,getContext(),this);
        recyclerView_search_category.setAdapter(searchAdapter);
    }

    @Override
    public void getSuccessAreaBySearch(MealsModelResponse mealsModelResponse) {
        searchAdapter = new SearchAdapter(mealsModelResponse,getContext(),this);
        recyclerView_search_category.setAdapter(searchAdapter);
    }

    @Override
    public void getSuccessIngredientsBySearch(MealsModelResponse mealsModelResponse) {
        searchAdapter = new SearchAdapter(mealsModelResponse,getContext(),this);
        recyclerView_search_category.setAdapter(searchAdapter);
    }

    @Override
    public void getSuccessNameBySearch(MealsModelResponse mealsModelResponse) {
        searchAdapter = new SearchAdapter(mealsModelResponse,getContext(),this);
        recyclerView_search_category.setAdapter(searchAdapter);

    }

    @Override
    public void getSuccessIdBySearch(MealsModelResponse mealsModelResponse) {
        mealsModel=mealsModelResponse.getMeals().get(0);
        //searchAdapter = new SearchAdapter(mealsModelResponse,getContext(),this);
     //   recyclerView_search_category.setAdapter(searchAdapter);

        SearchFragmentDirections.ActionSearchFragmentToDetailsFragment action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(mealsModel);
        Navigation.findNavController(getView()).navigate(action);

    }

    @Override
    public void getSuccessFirstLetterBySearch(MealsModelResponse mealsModelResponse) {
        searchAdapter = new SearchAdapter(mealsModelResponse,getContext(),this);
        recyclerView_search_category.setAdapter(searchAdapter);
    }

    @Override
    public void getFailedIdBySearch(String message) {
        Log.i("zxcv", "getFailedIdBySearch: "+message);
    }

    @Override
    public void onClickMealsInSearch(int position, String idMeal) {
        searchPresenter.searchByID(idMeal);



    }

}