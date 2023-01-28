package com.example.foodplanner.view.fragments;

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
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.example.foodplanner.view.adapters.DayAdapter;
import com.example.foodplanner.view.adapters.MealsAdapter;
import com.example.foodplanner.view.adapters.PlanMealsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanFragment extends Fragment implements PlanMealsAdapter.ListItemClickListener{
RecyclerView recyclerViewPlanMeals;
PlanMealsAdapter planMealsAdapter;
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



        planPresenter.getAllPlanMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<PlanMealsModel>>() {
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

                  }*/
                   setOfDays.add(planMealsModels.get(i).getDay());
               }
                   ArrayList<String> UniqListOfDays = new ArrayList(setOfDays);

               //    planMealsAdapter = new PlanMealsAdapter(planMealsModels, getContext(),UniqListOfDays);
                   dayAdapter = new DayAdapter(UniqListOfDays,planMealsModels, getContext(),PlanFragment.this);
                   recyclerViewPlanMeals.setAdapter(dayAdapter);

            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }
        });

    }

    @Override
    public void onClick(int position, PlanMealsModel planMealsModel) {

        planPresenter.deletePlan(planMealsModel);
        deletePlanMealFromFireStore(position,planMealsModel);
       // dayAdapter.notifyItemRemoved(position);

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
}