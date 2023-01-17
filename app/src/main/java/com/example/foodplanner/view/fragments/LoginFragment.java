package com.example.foodplanner.view.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginFragment extends Fragment implements SignUpFragmentInterface {

    Button googleBtn;
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
                googleAuth.authWithGoogle(result,firebaseAuth,LoginFragment.this,getContext());


            }
        }
    }



    @Override
    public void onSuccessResult() {
       // NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_nav_grav_main);
        Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_nav_graph);

    }

    @Override
    public void onFailureResult(String message) {

    }
}




