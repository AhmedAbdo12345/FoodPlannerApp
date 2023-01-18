package com.example.foodplanner.presenter.classes;

import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.example.foodplanner.model.api.MealsApiClient;
import com.example.foodplanner.presenter.interfaces.MealstInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsPresenter {

    MealstInterface mealstInterface;

    public MealsPresenter(MealstInterface homeFragmentInterface) {
        this.mealstInterface = homeFragmentInterface;
    }

    /*public void getMeals(){
        MealsApiClient.getINSTANE().().enqueue(new Callback<MealsModelResponse>() {
            @Override
            public void onResponse(Call<MealsModelResponse> call, Response<MealsModelResponse> response) {
                homeFragmentInterface.getSuccessMealsFromApi(response.body().getMealsModelArrayList());
            }

            @Override
            public void onFailure(Call<MealsModelResponse> call, Throwable t) {
                homeFragmentInterface.getFailureMealsFromApi(t.getMessage());
            }
        });

    }*/
   /* public void getSingleRandomMeals(){
        SingleObserver<MealsModelResponse> singleMealObserver = new SingleObserver<MealsModelResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MealsModelResponse mealsArrayListModel) {
         homeFragmentInterface.getSuccessSingleMealFromApi(mealsArrayListModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };

        Single<MealsModelResponse> single= MealsApiClient.getINSTANE().getSingleRandomMeal().subscribeOn(Schedulers.io());
        single.observeOn(AndroidSchedulers.mainThread()).subscribe(singleMealObserver);


    }*/
    public void getListOfRandomMeals() {

        ArrayList<Single<MealsModelResponse>> singleArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            singleArrayList.add(MealsApiClient.getINSTANE().getSingleRandomMeal());
        }
        Single<List<MealsModelResponse>> SinglemealsArrayListModel = Observable
                .fromIterable(singleArrayList)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<Single<MealsModelResponse>, ObservableSource<MealsModelResponse>>)
                        mealsArrayListModelSingle -> mealsArrayListModelSingle.toObservable()).collect(new Supplier<List<MealsModelResponse>>() {
                    @Override
                    public List<MealsModelResponse> get() throws Throwable {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<List<MealsModelResponse>, MealsModelResponse>() {
                    @Override
                    public void accept(List<MealsModelResponse> MealsModelRequests, MealsModelResponse MealsModelRequest) throws Throwable {
                        MealsModelRequests.add(MealsModelRequest);
                    }
                }).observeOn(AndroidSchedulers.mainThread());

        SinglemealsArrayListModel.subscribe(new SingleObserver<List<MealsModelResponse>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onSuccess(@NonNull List<MealsModelResponse> mealsModelRequestList) {
                if (mealsModelRequestList != null) {
                    mealstInterface.getSuccessMealsFromApi(mealsModelRequestList);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });





      /*  Observable
                .fromIterable(singleArrayList)
                .subscribe(new Observer<Single<MealsModelResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Single<MealsModelResponse> mealsArrayListModelSingle) {
                        mealsArrayListModelSingle.subscribe(new SingleObserver<MealsModelResponse>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull MealsModelResponse mealsArrayListModel) {
                                Log.d("zxc", "onSuccess: "+mealsArrayListModel.getMealsModelRequest().size());

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/
  /*  public void getRandomMeal() {
        ArrayList<Observable<MealsModelResponse>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(MealsApiClient.getINSTANE().getSingleRandomMeal());

        }
        Single<List<MealsModelResponse>> observable = Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())//UpStream operation
                .flatMap(new Function<Observable<MealsModelResponse>, ObservableSource<? extends MealsModelResponse>>() {
                    @Override
                    public ObservableSource<? extends MealsModelResponse> apply(Observable<MealsModelResponse> MealsModelRequestObservable) throws Throwable {
                        return MealsModelRequestObservable;
                    }
                })
                .collect(new Supplier<List<MealsModelResponse>>() {
                    @Override
                    public List<MealsModelResponse> get() throws Throwable {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<List<MealsModelResponse>, MealsModelResponse>() {
                    @Override
                    public void accept(List<MealsModelResponse> MealsModelRequests, MealsModelResponse MealsModelResponse) throws Throwable {
                        MealsModelRequests.add(MealsModelResponse);
                    }
                }).observeOn(AndroidSchedulers.mainThread());

        SingleObserver<List<MealsModelResponse>> singleMealObserver = new SingleObserver<List<MealsModelResponse>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<MealsModelResponse> MealsModelRequests) {
                mealstInterface.getSuccessMealsFromApi(MealsModelRequests);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
        observable.subscribe(singleMealObserver);
    }*/
    }
}