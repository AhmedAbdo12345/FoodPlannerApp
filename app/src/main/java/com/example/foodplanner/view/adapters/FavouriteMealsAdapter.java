package com.example.foodplanner.view.adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.favourite.FavModel;
import com.squareup.picasso.Picasso;
import java.util.List;


    public class FavouriteMealsAdapter extends RecyclerView.Adapter<com.example.foodplanner.view.adapters.FavouriteMealsAdapter.FavouriteViewHolder> {
        private List<FavModel> favMealsModelList;
        Context context;
        private static final String TAG = "FavouriteMealsAdapter";
        final private FavouriteMealsAdapter.ListItemClickListener mOnClickListener;

        public FavouriteMealsAdapter(List<FavModel> favMealsModelList, Context context,  com.example.foodplanner.view.adapters.FavouriteMealsAdapter.ListItemClickListener mOnClickListener) {
            this.favMealsModelList = favMealsModelList;
            this.context = context;
            this.mOnClickListener = mOnClickListener;
            Log.i(TAG, "FavouriteMealsAdapter: " + favMealsModelList);
        }


        public interface ListItemClickListener {
            void onClick(int position, FavModel favModel);
        }


        @NonNull
        @Override
        public FavouriteMealsAdapter.FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new com.example.foodplanner.view.adapters.FavouriteMealsAdapter.FavouriteViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_fav_meals, parent, false));

        }

        @Override
        public void onBindViewHolder(@NonNull com.example.foodplanner.view.adapters.FavouriteMealsAdapter.FavouriteViewHolder holder, int position) {

            if (favMealsModelList.get(position).getStrMealThumb() != null) {
                Picasso.get().load(favMealsModelList.get(position).getStrMealThumb()).into(holder.imgMeal);
            }
            holder.textViewTitle.setText(favMealsModelList.get(position).getStrMeal());
            holder.textViewCategory.setText(favMealsModelList.get(position).getStrCategory());

        }

        @Override
        public int getItemCount() {
            if (favMealsModelList != null){
                return favMealsModelList.size();

            }else {
                return 0;
            }
        }

        public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            ImageView imgMeal,imgDelte;
            TextView textViewTitle, textViewCategory;

            public FavouriteViewHolder (@NonNull View itemView) {
                super(itemView);
                imgMeal = itemView.findViewById(R.id.imageMealFav);
                textViewTitle = itemView.findViewById(R.id.mealNameFav);
                textViewCategory = itemView.findViewById(R.id.mealCategoryFav);
                imgDelte = itemView.findViewById(R.id.img_deleteMealFav);
                imgDelte.setOnClickListener(this);

            }


            @Override
            public void onClick(View v) {
                mOnClickListener.onClick( getAdapterPosition(),favMealsModelList.get(getAdapterPosition()));

            }
        }

    }


