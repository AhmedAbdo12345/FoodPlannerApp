package com.example.foodplanner.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.favourite.FavModel;
import com.example.foodplanner.view.fragments.FavouriteFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
//    Single<List<FavModel>> favMeal;
    List<FavModel> favMeal;
    FavouriteFragment favouriteFragment;

    public FavAdapter(FavouriteFragment favouriteFragment, Single<List<FavModel>> list) {
        this.favouriteFragment = favouriteFragment;
        this.favMeal = (List<FavModel>) list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_fav_meals, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(favMeal.get(position).getFavImg()).into(holder.imageMeal);
        holder.title.setText(favMeal.get(position).getFavName());
        holder.category.setText(favMeal.get(position).getFavCateogry());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMeal;
        TextView title, category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMeal = itemView.findViewById(R.id.imageMeal);
            title = itemView.findViewById(R.id.mealTitle);
            category = itemView.findViewById(R.id.mealDesc);

        }
    }

}
