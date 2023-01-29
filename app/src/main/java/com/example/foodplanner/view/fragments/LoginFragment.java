package com.example.foodplanner.view.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;


import com.example.foodplanner.model.ModelClasses.AuthModel;
import com.example.foodplanner.presenter.classes.SignUpFragmentPresenter;
import com.example.foodplanner.presenter.interfaces.GoogleAuthInterface;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.utils.GoogleAuth;
import com.example.foodplanner.utils.SaveUserDataInFireStore;
import com.example.foodplanner.utils.UserSharedPreference;
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

import dmax.dialog.SpotsDialog;


public class LoginFragment extends Fragment implements GoogleAuthInterface, LogInFragmentInterface {

    Button googleBtn;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    GoogleAuth googleAuth;

    LogInFragmentPresenter logInFragmentPresenter;
    EditText edtEmail, edtPassword;
    Button btnLogin,btnGuest;
    TextView tvForgotPassword,tvCreateNewAccount;
AlertDialog dialog;
String user="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleAuth = new GoogleAuth();
        googleSignInClient = googleAuth.createObjFromGoogleSignInClient(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();

        logInFragmentPresenter = new LogInFragmentPresenter(this,requireContext());

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

        dialog = new SpotsDialog(getContext());
        dialog.setTitle("Login");
btnGuest=view.findViewById(R.id.btn_Guest);
        edtEmail = view.findViewById(R.id.edt_email_login);
        edtPassword = view.findViewById(R.id.edt_password_login);
        btnLogin = view.findViewById(R.id.btn_Login);
        tvCreateNewAccount=view.findViewById(R.id.tv_create_new_Account);
        googleBtn = view.findViewById(R.id.btn_Login_googleAuth);
        tvForgotPassword=view.findViewById(R.id.tv_forgot_password);

        tvCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.signUpFragment);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                logInFragmentPresenter.logIn(edtEmail, edtPassword);
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user="Guest";
                UserSharedPreference userSharedPreference = UserSharedPreference.getInstance(getContext());
                userSharedPreference.saveDataInSharedPreference("user",user);
                Toast.makeText(getContext(), "Login With Guest", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeActivity);
                getActivity().finish();
            }
        });
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_forgotPasswordFragment);

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
    public void loginSucess(AuthResult authResult) {
        user=edtEmail.getText().toString();
        UserSharedPreference userSharedPreference = UserSharedPreference.getInstance(getContext());
        userSharedPreference.saveDataInSharedPreference("user",user);
        //navigate
        dialog.dismiss();
        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

        Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeActivity);
getActivity().finish();
    }

    @Override
    public void loginFaliure(String  message) {
        //navigate forget password
        dialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessGoogleAuthResult() {
        dialog.dismiss();
        Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeActivity);

    }

    @Override
    public void onFailureGoogleAuthResult(String message) {
        dialog.dismiss();
    }
}


