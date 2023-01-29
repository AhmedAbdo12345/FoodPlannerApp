package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.DisplayPlanModel;
import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.example.foodplanner.view.adapters.DayAdapter;
import com.example.foodplanner.view.adapters.PlanMealsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment implements  PlanMealsAdapter.AdapterConnector {
RecyclerView recyclerViewPlanMeals;
    DayAdapter dayAdapter;
    PlanPresenter planPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planPresenter = new PlanPresenter(getContext());


        recyclerViewPlanMeals=view.findViewById(R.id.recycler_view_Plan);
        recyclerViewPlanMeals.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewPlanMeals.setLayoutManager(gridLayoutManager);

        dayAdapter = new DayAdapter(ParentItemList(), requireContext(),this);

        recyclerViewPlanMeals.setAdapter(dayAdapter);


       /* planPresenter.getAllPlanMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<PlanMealsModel>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<PlanMealsModel> planMealsModels) {
               List<PlanMealsModel> listCurrentUser ;
                Set<String> setOfDays=new HashSet<>();
               for (int i=0;i<planMealsModels.size();i++){
                  /*if( planMealsModels.get(i).getUserId() != FirebaseAuth.getInstance().getCurrentUser().getEmail()){
                      planMealsModels.remove(planMealsModels.get(i));
                    //  setOfDays.add(listCurrentUser.get(i).getDay());

                  }
                   setOfDays.add(planMealsModels.get(i).getDay());
               }
                   ArrayList<String> UniqListOfDays = new ArrayList(setOfDays);

               //    planMealsAdapter = new PlanMealsAdapter(planMealsModels, getContext(),UniqListOfDays);
               //    dayAdapter = new DayAdapter(UniqListOfDays,planMealsModels, getContext(),PlanFragment.this);
                dayAdapter = new DayAdapter(ParentItemList(), getContext(),PlanFragment.this);

                recyclerViewPlanMeals.setAdapter(dayAdapter);

            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }
        });*/

    }


    public void deletePlanMealFromFireStore(int position, PlanMealsModel planMealsModel) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("PlanMeals")
                .document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("meals").document(planMealsModel.getIdMeal());


        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    getFragmentManager().beginTransaction().detach(PlanFragment.this).attach(PlanFragment.this).commit();

                Toast.makeText(requireContext(), "Meal is deleted Successful", Toast.LENGTH_SHORT).show();


                } else {

                    Log.i("PlanFragment", "onComplete: "+task.getException().getMessage().toString());

                }

            }
        });
    }
    List<DisplayPlanModel> itemList = new ArrayList<>();

    private List<DisplayPlanModel> ParentItemList() {

        planPresenter.getListPlanItem("SaturDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("SaturDay", planMealsModels);
                itemList.add(item);
            }
        });
        planPresenter.getListPlanItem("SunDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("Sunday", planMealsModels);
                itemList.add(item);
            }
        });
        planPresenter.getListPlanItem("MonDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("MonDay", planMealsModels);
                itemList.add(item);
            }
        });
        planPresenter.getListPlanItem("TuesDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("TuesDay", planMealsModels);
                itemList.add(item);
            }
        });
        planPresenter.getListPlanItem("WednesDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("WednesDay", planMealsModels);
                itemList.add(item);
            }
        });
        planPresenter.getListPlanItem("ThursDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("ThursDay", planMealsModels);
                itemList.add(item);
            }
        });
        planPresenter.getListPlanItem("FriDay").observe(getViewLifecycleOwner(), new Observer<List<PlanMealsModel>>() {
            @Override
            public void onChanged(List<PlanMealsModel> planMealsModels) {
                DisplayPlanModel item = new DisplayPlanModel("FriDay", planMealsModels);
                itemList.add(item);
                dayAdapter.setItemList(itemList);
            }
        });

        return itemList;
    }

    @Override
    public void sendData(PlanMealsModel meal) {

    }

    @Override
    public void callRepo(PlanMealsModel meal, int position) {
        planPresenter.deletePlan(meal);
        deletePlanMealFromFireStore(position,meal);
    }
}