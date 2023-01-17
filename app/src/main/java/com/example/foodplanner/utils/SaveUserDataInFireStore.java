
package com.example.foodplanner.utils;

import androidx.annotation.NonNull;

import com.example.foodplanner.model.ModelClasses.AuthModel;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SaveUserDataInFireStore {
    public static void saveDataInFStore(AuthModel authModel, SignUpFragmentInterface signUpFragmentInterface){
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("User").document(authModel.getUserId());

        documentReference.set(authModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                signUpFragmentInterface.onSuccessResult();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                signUpFragmentInterface.onFailureResult(e.getMessage().toString());
            }
        });

    }
}