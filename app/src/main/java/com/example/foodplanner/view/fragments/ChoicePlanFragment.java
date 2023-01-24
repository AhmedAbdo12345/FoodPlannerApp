package com.example.foodplanner.view.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.ModelClasses.MealsTypeModel;
import com.example.foodplanner.model.ModelClasses.PlanModel;
import com.example.foodplanner.model.database.PlanMealsModel;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.example.foodplanner.view.adapters.DayPlanAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ChoicePlanFragment extends Fragment {
    Button buttonAddNewPlan;
    ArrayList<String> daysArrayList = new ArrayList<>();
    ArrayList<MealsTypeModel> mealsTypeModelArrayList = new ArrayList<>();
    MealsModel mealsModel;
PlanPresenter planPresenter;
    RecyclerView recyclerView;
    SwitchCompat switchCompatDays, switchCompatBreakFast, switchCompatLunch, switchCompatDinner;
    String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

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
        planPresenter=new PlanPresenter(getContext());
        buttonAddNewPlan = view.findViewById(R.id.btn_AddNewPlann);
         recyclerView = view.findViewById(R.id.planRecycleView);
        DayPlanAdapter dayCardViewAdapter = new DayPlanAdapter(days);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dayCardViewAdapter);

       /* buttonAddNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAddNewPlan.setEnabled(false);
                getDataFromRecyclerView(v);
                PlanMealsModel planModel = new PlanMealsModel( FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        mealsModel.getIdMeal(), mealsModel.getStrMeal(), mealsModel.getStrCategory()
                        , mealsModel.getStrArea(), mealsModel.getStrInstructions(), mealsModel.getStrMealThumb(), mealsModel.getStrYoutube(), daysArrayList, mealsTypeModelArrayList);

                planPresenter.insertPlan(planModel);
              //  AddNewPlan();


            }
        });*/
    }
void getDataFromRecyclerView(View v){
    for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
        MealsTypeModel mealsTypeModel = new MealsTypeModel();
        int counter = 0;
        v = recyclerView.getChildAt(i);

        switchCompatDays = v.findViewById(R.id.daySwitchCompat);
        switchCompatBreakFast = v.findViewById(R.id.breakfastSwitchCompat);
        switchCompatLunch = v.findViewById(R.id.lunchSwitchCompat);
        switchCompatDinner = v.findViewById(R.id.dinnerSwitchCompat);
        if (switchCompatDays.isChecked()) {
            daysArrayList.add(days[i]);

            if (switchCompatBreakFast.isChecked()) {
                mealsTypeModel.setBreakfast("BreakFast");
                counter++;
            }

            if (switchCompatLunch.isChecked()) {
                mealsTypeModel.setLunch("Lunch");
                counter++;

            }

            if (switchCompatDinner.isChecked()) {
                mealsTypeModel.setDinner("Dinner");
                counter++;

            }
            if (counter > 0) {
                mealsTypeModelArrayList.add(mealsTypeModel);
            } else {
                Toast.makeText(getContext(), "Please Choice Type of Meal", Toast.LENGTH_SHORT).show();
                mealsTypeModelArrayList.clear();
                daysArrayList.clear();
            }
        }
    }

}
  /*  public void AddNewPlan() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("Plan").document();
        PlanModel planModel = new PlanModel(documentReference.getId(), FirebaseAuth.getInstance().getCurrentUser().getUid(),
                mealsModel.getIdMeal(), mealsModel.getStrMeal(), mealsModel.getStrCategory()
                , mealsModel.getStrArea(), mealsModel.getStrInstructions(), mealsModel.getStrMealThumb(), mealsModel.getStrYoutube(), daysArrayList, mealsTypeModelArrayList);

        documentReference.set(planModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                   // AddDays(documentReference.getId());
                    buttonAddNewPlan.setEnabled(true);
                    Toast.makeText(getContext(), "plan Add Successfuly in FireStore", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();

                }

            }
        });
}*/



}