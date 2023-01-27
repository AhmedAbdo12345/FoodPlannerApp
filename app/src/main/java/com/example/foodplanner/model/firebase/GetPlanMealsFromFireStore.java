package com.example.foodplanner.model.firebase;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.classes.PlanPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GetPlanMealsFromFireStore {


    public static void getPlanFromFireStore(Context context) {
        FirebaseFirestore.getInstance().collection("PlanMeals").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("meals").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("LogInFragmentPresenter", "onSuccess:  EMPTY");
                            return;
                        } else {
                            List<PlanMealsModel> types = documentSnapshots.toObjects(PlanMealsModel.class);
                            ArrayList<PlanMealsModel> listOfStrings = new ArrayList<>(types.size());
                            listOfStrings.addAll(types);
                            //-----------------This line To inser All Plan in Room ---------------------
                            PlanPresenter planPresenter = new PlanPresenter(context);
                            planPresenter.insertAllPlan(listOfStrings);
                            //----------------------------------------------------------------------

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("LogInFragmentPresenter", "onFailure: "+e.toString());

                    }
                });
    }

}
