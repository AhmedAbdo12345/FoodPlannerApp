package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChoicePlanFragment extends Fragment {
    Button buttonAddNewPlan;
    MealsModel mealsModel;
    PlanPresenter planPresenter;
    String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};


    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choice_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsModel = DetailsFragmentArgs.fromBundle(getArguments()).getMeal();
        planPresenter = new PlanPresenter(requireContext());
        buttonAddNewPlan = view.findViewById(R.id.btn_AddNewPlann);
        radioGroup = view.findViewById(R.id.radioGroupDays);


        buttonAddNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton != null) {
                    String day = radioButton.getText().toString();

                    PlanMealsModel planMealsModel = new PlanMealsModel(mealsModel.getIdMeal(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), day
                            , mealsModel.getStrMeal(), mealsModel.getStrCategory(), mealsModel.getStrArea()
                            , mealsModel.getStrInstructions(), mealsModel.getStrMealThumb(), mealsModel.getStrYoutube());

                    planPresenter.insertPlan(planMealsModel);
                    addPlan(planMealsModel);
                } else {
                    Toast.makeText(requireContext(), "Please Choice Day To Add new Plan", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }





    private void addPlan(PlanMealsModel planMealsModel) {
        FirebaseFirestore.getInstance().collection("PlanMeals").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("meals")
                .document(planMealsModel.getIdMeal())
                .set(planMealsModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       // Toast.makeText(context, "The Meal Added To Favorite", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("ChoicePlanFragment", "onFailure: "+e.toString());
                    }
                });
    }
}




