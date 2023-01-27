package com.example.foodplanner.presenter.repository;

import android.content.Context;
import android.widget.Toast;

import com.example.foodplanner.model.database.plan.DaoMeals;
import com.example.foodplanner.model.database.plan.DatabaseMeals;
import com.example.foodplanner.model.database.plan.PlanMealsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanRepository {
    private Context context;
    private DaoMeals daoMeals;
    private Single<List<PlanMealsModel>> listLiveData;


    public PlanRepository(Context context){
        this.context=context;
        DatabaseMeals db = DatabaseMeals.getInstance(context);
        daoMeals = db.taskDao();
        listLiveData = daoMeals.loadAllData();
    }

    public Single<List<PlanMealsModel>> getAllPlansFromDatabase() {
        return listLiveData;
    }

    public void insert(PlanMealsModel model) {
        daoMeals.insertPlanData(model).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {

               Toast.makeText(context, "Add Successfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
    public void deletePlan(PlanMealsModel model) {
        daoMeals.deletePlanMeal(model).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d){

            }
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
    public void deleteTablePlan() {
        daoMeals.deletePlanTable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d){

            }
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }


    public void insertAllPlanInRoom(ArrayList<PlanMealsModel> planMealsModelArrayList){
        new Thread(() ->
                daoMeals.insertAllPlan(planMealsModelArrayList)).start();
    }

}
