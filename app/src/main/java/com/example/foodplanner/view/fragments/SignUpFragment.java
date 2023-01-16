package com.example.foodplanner.view.fragments;

import android.content.Intent;
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
import com.example.foodplanner.presenter.classes.SignUpFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.utils.GoogleAuth;
import com.example.foodplanner.utils.NavigatorClass;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment implements SignUpFragmentInterface {
    EditText editTextName,editTextEmail,editTextPassword;
    Button buttonSignUp;
    SignInButton buttonGoogleAuth;
    SignUpFragmentPresenter signUpFragmentPresenter;

    private GoogleSignInClient mGoogleSignInClient;
    GoogleAuth googleAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName=view.findViewById(R.id.edt_name);
        editTextEmail=view.findViewById(R.id.edt_email);
        editTextPassword=view.findViewById(R.id.edt_Password);
        buttonSignUp=view.findViewById(R.id.btn_signup);
        buttonGoogleAuth=view.findViewById(R.id.btn_googleAuth);
        signUpFragmentPresenter=new SignUpFragmentPresenter(this);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editTextName.getText().toString();
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    signUpFragmentPresenter.createUser(name,email,password);
                }
            }
        });
        googleAuth=new GoogleAuth();
        mGoogleSignInClient=googleAuth.createRequest(getActivity());
        buttonGoogleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleAuth.SignInGoogleAuth(mGoogleSignInClient,getActivity());

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        googleAuth.onActivityResultMyCodeFunction(requestCode,data,getActivity());

    }




    @Override
    public void onSuccessResult() {
        Toast.makeText(getContext(), "Saving User Info Succefully", Toast.LENGTH_SHORT).show();
        NavigatorClass.Navigate(getContext(),NavigatorClass.GO_FROM_SIGNUP_TO_LOGIN,requireView());

    }

    @Override
    public void onFailureResult(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }
}