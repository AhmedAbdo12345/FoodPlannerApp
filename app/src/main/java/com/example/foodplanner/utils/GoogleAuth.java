package com.example.foodplanner.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.foodplanner.R;
import com.example.foodplanner.view.activities.MainActivity;
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

    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();



    public GoogleSignInClient createRequest(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);

        /*----this line to ask me  to choice account before SignIn  -----------*/
        /*----Without this line if my device has one account , automaic Sign in By this account-----*/
        mGoogleSignInClient.revokeAccess();

        return mGoogleSignInClient;
    }


    public void SignInGoogleAuth(GoogleSignInClient signInClient, Activity activity) {
        Intent signInIntent = signInClient.getSignInIntent();

        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResultMyCodeFunction(int requestCode, Intent data, Activity activity) {

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account, activity);
                Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show();

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(activity, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct, Activity activity) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        String email = user.getEmail().toString();
                                        String name = user.getDisplayName().toString();
                                        SaveUserDataInFirestore(activity, user.getUid(), name, email);

                                        Intent intent = new Intent(activity, MainActivity.class);
                                        activity.startActivity(intent);
                                    }


                                } else {
                                    Toast.makeText(activity, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void SaveUserDataInFirestore(Activity activity, String idUser, String name, String email) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = firestore.collection("User").document(idUser);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("Name", name);
        userInfo.put("Email", email);
        documentReference.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Saving User Info Succefully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
