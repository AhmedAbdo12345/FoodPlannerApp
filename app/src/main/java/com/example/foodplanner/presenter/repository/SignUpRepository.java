package com.example.foodplanner.presenter.repository;

import androidx.annotation.NonNull;

import com.example.foodplanner.model.ModelClasses.AuthModel;
import com.example.foodplanner.utils.SaveUserDataInFireStore;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpRepository {
    private FirebaseFirestore firestore;
    private SignUpFragmentInterface signUpFragmentInterface;

    public SignUpRepository(SignUpFragmentInterface authFragmentInterface) {
        firestore = FirebaseFirestore.getInstance();
        this.signUpFragmentInterface = authFragmentInterface;
    }

    public void createNewUser(String nameuser, String emailUser, String passwordUser) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    String idUser = firebaseAuth.getCurrentUser().getUid();
                    user.sendEmailVerification();
                 //   saveUserDataInFirestore(idUser, nameuser, emailUser);
                    AuthModel authModel = new AuthModel(idUser, nameuser, emailUser);
                    SaveUserDataInFireStore.saveDataInFStore(authModel,signUpFragmentInterface);

                }
            }
        });
    }

   /* public void saveUserDataInFirestore(String idUser, String name, String email) {
        AuthModel authModel = new AuthModel(idUser, name, email);
        DocumentReference documentReference = firestore.collection("User").document(idUser);

        documentReference.set(authModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                authFragmentInterface.onSuccessResult();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                authFragmentInterface.onFailureResult(e.getMessage().toString());
            }
        });

    }*/
}
