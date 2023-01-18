package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.classes.LogInFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.LogInFragmentInterface;
import com.google.firebase.auth.AuthResult;

public class LoginFragment extends Fragment implements LogInFragmentInterface {

    LogInFragmentPresenter logInFragmentPresenter;
    EditText email, password;
    Button login;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logInFragmentPresenter = new LogInFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.sign_email);
        password = view.findViewById(R.id.sign_Password);
        login = view.findViewById(R.id.signInBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInFragmentPresenter.logIn(email, password);
            }
        });
    }

    @Override
    public void loginSucess(AuthResult authResult) {
        //navigate
        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFaliure(@NonNull Exception e) {
        //navigate forget password
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
    }

}