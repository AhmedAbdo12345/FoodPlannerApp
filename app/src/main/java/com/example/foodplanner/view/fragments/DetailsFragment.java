package com.example.foodplanner.view.fragments;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;

import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.example.foodplanner.presenter.classes.FavPresenter;
import com.example.foodplanner.utils.NetworkConnection;
import com.example.foodplanner.utils.UserSharedPreference;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    ImageView imgMeal;
    TextView tvTitleMeal, tvCategoryMeal, tvInstructions, tvArea;
    YouTubePlayerView youTubePlayerView;
    Button buttonAddPlan;
    MealsModel model;
    ImageButton imageButtonFav;
    FavPresenter favPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButtonFav = view.findViewById(R.id.imgButtonFav);
        imgMeal = view.findViewById(R.id.img_meal_details);
        tvTitleMeal = view.findViewById(R.id.tv_title_details);
        tvCategoryMeal = view.findViewById(R.id.tv_meal_category_details);
        tvInstructions = view.findViewById(R.id.tv_instructions_details);
        tvArea = view.findViewById(R.id.tv_area_details);
        buttonAddPlan = view.findViewById(R.id.btn_Plan);


        model = DetailsFragmentArgs.fromBundle(getArguments()).getMeal();
        tvTitleMeal.setText(model.getStrMeal());
        tvCategoryMeal.setText(model.getStrCategory());
        tvInstructions.setText(model.getStrInstructions());
        tvArea.setText(model.getStrArea());

        if (model.getStrMealThumb() != null) {
            Picasso.get().load(model.getStrMealThumb()).into(imgMeal);
        }
//----------------------------------
        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                try {
                    String[] videoId = model.getStrYoutube().split("=");
                    youTubePlayer.loadVideo(videoId[1], 0);

                } catch (Exception e) {
              //      Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*----------------------------------------------------------*/


        String user = UserSharedPreference.getInstance(getContext()).getDataFromSharedPreference("user");
        if (user.equals("Guest") || (!NetworkConnection.isNetworkAvailable(getContext()))) {
            imageButtonFav.setEnabled(false);
            buttonAddPlan.setEnabled(false);
            imageButtonFav.setVisibility(View.GONE);
            buttonAddPlan.setVisibility(View.GONE);
        } else {
            imageButtonFav.setEnabled(true);
            buttonAddPlan.setEnabled(true);
            imageButtonFav.setVisibility(View.VISIBLE);
            buttonAddPlan.setVisibility(View.VISIBLE);

            /*----------------------------------------------------------*/
            buttonAddPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (model != null) {
                        DetailsFragmentDirections.ActionDetailsFragmentToChoicePlanFragment action = DetailsFragmentDirections.actionDetailsFragmentToChoicePlanFragment(model);
                        Navigation.findNavController(getView()).navigate(action);
                    }
                }
            });
            favPresenter = new FavPresenter(getContext());
            FavModel favModel = new FavModel(model.getIdMeal(), FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                    model.getStrMeal(), model.getStrCategory(), model.getStrArea(), model.getStrInstructions(), model.getStrMealThumb(), model.getStrYoutube());
            imageButtonFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favPresenter.insertFav(favModel);
                    // addFavinFireStore(favModel);
                }
            });

        }
        /*----------------------------------------------------------*/

    }

    public void addFavinFireStore(FavModel favModel) {
        FirebaseFirestore.getInstance().collection("Fav").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("meals")
                .document(favModel.getIdMeal())
                .set(favModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Toast.makeText(context, "The Meal Added To Favorite", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("DetailsFragment", "onFailure: " + e.toString());
                    }
                });
    }

}