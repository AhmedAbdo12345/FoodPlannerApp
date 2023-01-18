package com.example.foodplanner.presenter.interfaces;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthResult;

public interface LogInFragmentInterface {
    void loginSucess(AuthResult authResult);
    void loginFaliure(@NonNull Exception e);

}
