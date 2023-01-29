package com.example.foodplanner.presenter.classes;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.model.firebase.GetPlanMealsFromFireStore;
import com.example.foodplanner.presenter.interfaces.LogInFragmentInterface;
import com.example.foodplanner.utils.ConstantsClass;
import com.example.foodplanner.view.activities.AuthActivity;
import com.example.foodplanner.view.fragments.LoginFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInFragmentPresenter {

    LogInFragmentInterface logInFragmentInterface;
    FirebaseAuth firebaseAuth;
    String email, password;
    Context context;

    public LogInFragmentPresenter(LogInFragmentInterface logInFragmentInterface, Context context) {
        this.logInFragmentInterface = logInFragmentInterface;
        this.context = context;
    }

    public void logIn(EditText edtEmail, EditText edtPassword) {
        firebaseAuth = FirebaseAuth.getInstance();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!password.isEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                    ConstantsClass.setEMAIL(email);
                                    //  GetPlanMealsFromFireStore.getAllPlan(context);
                                    GetPlanMealsFromFireStore.getPlanFromFireStore(context);
                                    GetPlanMealsFromFireStore.getFavFromFireStore(context);

                                    logInFragmentInterface.loginSucess(authResult);


                                }else {
                                    firebaseAuth.getCurrentUser().sendEmailVerification();
                                    String message="Check Email to Verification before login";
                                    //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                    logInFragmentInterface.loginFaliure(message);
                                    firebaseAuth.signOut();

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                logInFragmentInterface.loginFaliure("Your  Email or Password is InValid");
                            }
                        });
            } else {
                edtPassword.setError("Please enter your password");
            }

        } else if (email.isEmpty()) {
            edtEmail.setError("please enter your email");
        } else {
            edtEmail.setError("please enter a valid email");
        }
    }




}
