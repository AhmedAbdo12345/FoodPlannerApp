package com.example.foodplanner.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;


import com.example.foodplanner.model.ModelClasses.AuthModel;
import com.example.foodplanner.presenter.classes.SignUpFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.utils.GoogleAuth;
import com.example.foodplanner.utils.SaveUserDataInFireStore;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.example.foodplanner.presenter.classes.LogInFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.LogInFragmentInterface;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment implements SignUpFragmentInterface, LogInFragmentInterface {

    Button googleBtn;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    GoogleAuth googleAuth;

    LogInFragmentPresenter logInFragmentPresenter;
    EditText email, password;
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleAuth = new GoogleAuth();
        googleSignInClient = googleAuth.createObjFromGoogleSignInClient(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        logInFragmentPresenter = new LogInFragmentPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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

        googleBtn = view.findViewById(R.id.btn_Login_googleAuth);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GoogleSignInAccount result = null;
        if (requestCode == 100) {
            try {
                result = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();
            } catch (Exception e) {
                Log.i("zxc", "onActivityResult: Error  " + e.toString());
            }
            if (result != null) {
                googleAuth.authWithGoogle(result, firebaseAuth, LoginFragment.this, getContext());
            }
        }
    }


    @Override
    public void onSuccessResult() {
        // NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_nav_grav_main);
        Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeActivity);

    }

    @Override
    public void onFailureResult(String message) {

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


