package com.example.foodplanner.view.fragments;

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
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.ModelClasses.MealsTypeModel;
import com.example.foodplanner.model.ModelClasses.PModel;
import com.example.foodplanner.model.ModelClasses.PlanModel;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.example.foodplanner.view.adapters.DayPlanAdapter;
import com.example.foodplanner.presenter.interfaces.DaySelectedInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChoicePlanFragment extends Fragment implements  DaySelectedInterface {
    Map<String, PlanModel> mapMeal = new HashMap<>();
    PlanModel planModel;
    Map<String,PModel> modelMap=new HashMap<>();
ArrayList<Integer> arrayListActivePosition=new ArrayList<>();
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
        DayPlanAdapter dayCardViewAdapter = new DayPlanAdapter(days,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dayCardViewAdapter);
         planModel=new PlanModel(FirebaseAuth.getInstance().getUid(),mealsModel.getIdMeal(),mealsModel.getStrMeal(),mealsModel.getStrMealThumb());

        buttonAddNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAddNewPlan.setEnabled(false);

                if (getDataFromRecyclerView(v)){
                   // AddNewPlan();
                }


            }
        });
    }
boolean getDataFromRecyclerView(View v){
        boolean haveDaySelected=false;
    int minimumClick=0;
    for (int i=0;i<arrayListActivePosition.size();i++){
     //   Log.i("zxcv", "onClick: "+arrayListActivePosition.get(i).toString());
        v=recyclerView.getChildAt(arrayListActivePosition.get(i));
        switchCompatDays=v.findViewById(R.id.daySwitchCompat);


        //Map<String, PlanModel> mapMeal = new HashMap<>();
       // MealsTypeModel mealsTypeModel = new MealsTypeModel();
        int counter = 0;

        if (switchCompatDays.isChecked()) {
            switchCompatBreakFast =(SwitchCompat) v.findViewById(R.id.breakfastSwitchCompat);
            switchCompatLunch = (SwitchCompat)v.findViewById(R.id.lunchSwitchCompat);
            switchCompatDinner =(SwitchCompat) v.findViewById(R.id.dinnerSwitchCompat);
            daysArrayList.add(days[arrayListActivePosition.get(i)]);

            if (switchCompatBreakFast.isChecked()) {
                mapMeal.put("Breakfast",planModel);

               // mealsTypeModel.setBreakfast("BreakFast");
                counter++;
            }

            if (switchCompatLunch.isChecked()) {
                mapMeal.put("Lunch", planModel);
                //mealsTypeModel.setLunch("Lunch");
                counter++;
            }

            if (switchCompatDinner.isChecked()) {
                mapMeal.put("Dinner", planModel);
               // mealsTypeModel.setDinner("Dinner");
                counter++;
            }
            if (counter > 0) {
                haveDaySelected=true;
                PModel pModel=new PModel(days[arrayListActivePosition.get(i)],mapMeal);
                modelMap.put(days[arrayListActivePosition.get(i)],pModel);
                unchickSwitch();
              //  mealsTypeModelArrayList.add(mealsTypeModel);
            } else {
                Toast.makeText(getContext(), "Please Choice Type of Meal", Toast.LENGTH_SHORT).show();
              //  mealsTypeModelArrayList.clear();
              //  daysArrayList.clear();
            }

        }
    }
return haveDaySelected;
}
    public void AddNewPlan() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("Plan").document(FirebaseAuth.getInstance().getUid());

        documentReference.set(modelMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
}

void unchickSwitch(){
    switchCompatDays.setChecked(false);
    switchCompatBreakFast.setChecked(false);
    switchCompatLunch.setChecked(false);
    switchCompatDinner.setChecked(false);
}
  /*  @Override
    public void onClickDay(String day, int position) {
        if (arrayListActivePosition.contains(position) == false){
            arrayListActivePosition.add(position);

        }
    }*/


    @Override
    public void onBreakfastSelected(String day, boolean seleted) {
        if (seleted){
            mapMeal.put("Breakfast",planModel);
        }else {
            mapMeal.remove("Breakfast");
        }
    }
    @Override
    public void onLunchSelected(String day, boolean seleted) {
        if (seleted){
            mapMeal.put("Lunch",planModel);
        }else {
            mapMeal.remove("Lunch");
        }
    }

    @Override
    public void onDinnerSelected(String day, boolean seleted) {
        if (seleted){
            mapMeal.put("Dinner",planModel);
        }else {
            mapMeal.remove("Dinner");
        }
    }

    @Override
    public void onDaySelected(String name, boolean added) {
if (added == false){

}
    }
}