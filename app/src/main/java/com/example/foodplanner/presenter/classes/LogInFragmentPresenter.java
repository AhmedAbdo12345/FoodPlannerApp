package com.example.foodplanner.presenter.classes;

import android.util.Patterns;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.foodplanner.presenter.interfaces.LogInFragmentInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInFragmentPresenter {

    LogInFragmentInterface logInFragmentInterface;
    FirebaseAuth firebaseAuth;
    String email, password;

    public LogInFragmentPresenter(LogInFragmentInterface logInFragmentInterface) {
        this.logInFragmentInterface = logInFragmentInterface;
    }

    public void logIn(EditText edtEmail,EditText edtPassword){
        firebaseAuth = FirebaseAuth.getInstance();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        if(!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!password.isEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                logInFragmentInterface.loginSucess(authResult);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                logInFragmentInterface.loginFaliure(e);
                            }
                        });
            } else {
                edtPassword.setError("Please enter your password");
            }

        } else if (email.isEmpty()){
            edtEmail.setError("please enter your email");
        }else{
            edtEmail.setError("please enter a valid email");
        }
    }
}
