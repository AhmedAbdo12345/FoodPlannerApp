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
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.classes.SignUpFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.utils.GoogleAuth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment implements SignUpFragmentInterface {
    EditText editTextName,editTextEmail,editTextPassword;
    Button buttonSignUp,googleBtn;
    SignUpFragmentPresenter signUpFragmentPresenter;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    GoogleAuth googleAuth;

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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        editTextName=view.findViewById(R.id.edt_name);
        editTextEmail=view.findViewById(R.id.edt_email);
        editTextPassword=view.findViewById(R.id.edt_Password);
        buttonSignUp=view.findViewById(R.id.btn_signup);
        googleBtn=view.findViewById(R.id.btn_googleAuth);
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
        if (requestCode == 100) {
            GoogleSignInAccount result = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();
            if (result != null) {
                googleAuth.authWithGoogle(result,firebaseAuth,SignUpFragment.this,getContext());


            }
        }
    }



    @Override
    public void onSuccessResult() {
        // NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_nav_grav_main);
        Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_nav_grav_main);

    }

    @Override
    public void onFailureResult(String message) {

    }
}

