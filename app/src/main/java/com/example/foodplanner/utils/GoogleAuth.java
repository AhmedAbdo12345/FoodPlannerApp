package com.example.foodplanner.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.AuthModel;
import com.example.foodplanner.presenter.interfaces.GoogleAuthInterface;
import com.example.foodplanner.presenter.interfaces.SignUpFragmentInterface;
import com.example.foodplanner.view.activities.MainActivity;
import com.example.foodplanner.view.fragments.LoginFragment;
import com.example.foodplanner.view.fragments.SignUpFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GoogleAuth {
    GoogleSignInClient googleSignInClient;


    public GoogleSignInClient createObjFromGoogleSignInClient(Activity activity) {
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(activity,options);

        /*----this line to ask me  to choice account before SignIn  -----------*/
        /*----Without this line if my device has one account , automaic Sign in By this account-----*/
        googleSignInClient.revokeAccess();

        return googleSignInClient;
       //firebaseAuth = FirebaseAuth.getInstance();
    }

    public void authWithGoogle(GoogleSignInAccount result, FirebaseAuth firebaseAuth, GoogleAuthInterface googleAuthInterface, Context context) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(result.getIdToken(), null);
        try {
            firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // To dismiss the dialog
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String email = user.getEmail().toString();
                            String name = user.getDisplayName().toString();
                            ConstantsClass.setEMAIL(email);

                            // SaveUserDataInFirestore(activity,user.getUid(),name,email);
                            AuthModel authModel = new AuthModel(user.getUid(), name, email);
                            SaveUserDataInFireStore.saveDataInFStore(authModel, googleAuthInterface);
                            Toast.makeText(context, "Gooood T.", Toast.LENGTH_SHORT).show();


                        }


                    } else {
                        Toast.makeText(context, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Log.i("auth", "authWithGoogle: " + e.getMessage());
        }
    }
}
