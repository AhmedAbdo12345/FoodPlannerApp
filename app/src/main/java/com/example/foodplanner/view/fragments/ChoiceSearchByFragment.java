package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplanner.R;
import com.example.foodplanner.utils.ConstantsClass;


public class ChoiceSearchByFragment extends Fragment {
Button buttonCategory,buttonArea,buttonIngrediant;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice_search_by, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonArea=view.findViewById(R.id.btn_search_by_area);
        buttonCategory=view.findViewById(R.id.btn_search_by_category);
        buttonIngrediant=view.findViewById(R.id.btn_search_by_ingredient);

        buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        ChoiceSearchByFragmentDirections.ActionChoiceSearchByFragmentToSearchFragment action=
                ChoiceSearchByFragmentDirections.actionChoiceSearchByFragmentToSearchFragment(ConstantsClass.CATEGORY);
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        buttonArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceSearchByFragmentDirections.ActionChoiceSearchByFragmentToSearchFragment action=
                        ChoiceSearchByFragmentDirections.actionChoiceSearchByFragmentToSearchFragment(ConstantsClass.Area);
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        buttonIngrediant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceSearchByFragmentDirections.ActionChoiceSearchByFragmentToSearchFragment action=
                        ChoiceSearchByFragmentDirections.actionChoiceSearchByFragmentToSearchFragment(ConstantsClass.Ingredients);
                Navigation.findNavController(getView()).navigate(action);
            }
        });
    }
}