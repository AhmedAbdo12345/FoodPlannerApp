package com.example.foodplanner.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.classes.SignUpFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.GoogleAuthInterface;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.utils.GoogleAuth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment implements SignUpFragmentInterface , GoogleAuthInterface {
EditText editTextName,editTextEmail,editTextPassword;
    Button googleBtn,buttonSignUp;
    SignUpFragmentPresenter signUpFragmentPresenter;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    GoogleAuth googleAuth;

    TextView tvAlreadyHaveAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleAuth = new GoogleAuth();
        googleSignInClient = googleAuth.createObjFromGoogleSignInClient(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       tvAlreadyHaveAccount=view.findViewById(R.id.tv_already_have_account);
        tvAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);

            }
        });
       editTextName=view.findViewById(R.id.edt_name);
        editTextEmail=view.findViewById(R.id.edt_email);
        editTextPassword=view.findViewById(R.id.edt_Password);
        googleBtn=view.findViewById(R.id.btn_googleAuth);
        buttonSignUp=view.findViewById(R.id.btn_signUp);
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
        if (requestCode == 100) {
            GoogleSignInAccount result = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();
            if (result != null) {
               googleAuth.authWithGoogle(result,firebaseAuth,SignUpFragment.this,getContext());


            }
        }
    }



    @Override
    public void onSuccessSignUPResult() {
        // NavHostFragment.findNavController(this).navigate(R.id.action_signUpFragment_to_loginFragment);
        Navigation.findNavController(getView()).navigate(R.id.action_signUpFragment_to_loginFragment);

    }

    @Override
    public void onFailureSignUPResult(String message) {

    }

    @Override
    public void onSuccessGoogleAuthResult() {

        Navigation.findNavController(getView()).navigate(R.id.action_signUpFragment_to_homeActivity);

    }

    @Override
    public void onFailureGoogleAuthResult(String message) {

    }
}

