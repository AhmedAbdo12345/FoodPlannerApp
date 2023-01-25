package com.example.foodplanner.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;

import com.example.foodplanner.model.database.favourite.FavDao;
import com.example.foodplanner.model.database.favourite.FavModel;
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
    ImageButton fav, unFav;
    FavDao favDao;
    FavModel favModel;

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

        imgMeal = view.findViewById(R.id.img_meal_details);
        tvTitleMeal = view.findViewById(R.id.tv_title_details);
        tvCategoryMeal = view.findViewById(R.id.tv_meal_category_details);
        tvInstructions = view.findViewById(R.id.tv_instructions_details);
        tvArea = view.findViewById(R.id.tv_area_details);
        buttonAddPlan=view.findViewById(R.id.btn_Plan);

         model = DetailsFragmentArgs.fromBundle(getArguments()).getMeal();
        tvTitleMeal.setText(model.getStrMeal());
        tvCategoryMeal.setText(model.getStrCategory());
        tvInstructions.setText(model.getStrInstructions());
        tvArea.setText(model.getStrArea());


      favModel = new FavModel(model.getIdMeal(), model.getStrMeal(), model.getStrCategory(),model.getStrMealThumb());


        fav = view.findViewById(R.id.favBtn);
        unFav = view.findViewById(R.id.unfavBtn);

        if (model.getStrMealThumb() != null) {
            Picasso.get().load(model.getStrMealThumb()).into(imgMeal);
        }
//----------------------------------

        //add and remove fav
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favDao.inserFavtMeal(favModel);
            }
        });

        unFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favDao.deleteFavMeal(favModel);
            }
        });


        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String[] videoId = model.getStrYoutube().split("=");

                youTubePlayer.loadVideo(videoId[1], 0);
            }
        });
        /*----------------------------------------------------------*/
        buttonAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model !=null) {
                    DetailsFragmentDirections.ActionDetailsFragmentToChoicePlanFragment action = DetailsFragmentDirections.actionDetailsFragmentToChoicePlanFragment(model);
                    Navigation.findNavController(getView()).navigate(action);
                }
            }
        });
    }


}