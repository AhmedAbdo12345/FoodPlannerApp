package com.example.foodplanner.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.MealsModel;
import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MealByCategoryAdapter extends RecyclerView.Adapter<MealByCategoryAdapter.RecyclerViewHolder> {
    private CategoryModelResponse categoryModelResponses;
    private static MealsModelResponse meals;
    private Context context;
    static  private MealByCategoryAdapter.ListItemClickListener mOnClickListener;

    public MealByCategoryAdapter(Context context,MealsModelResponse meals,MealByCategoryAdapter.ListItemClickListener mOnClickListene) {
        this.meals = meals;
        this.context = context;
        this.mOnClickListener=mOnClickListene;
    }

    public interface ListItemClickListener {
        void onClick(int position, MealsModel meals);
    }
    @NonNull
    @Override
    public MealByCategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new com.example.foodplanner.view.adapters.MealByCategoryAdapter.RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_meal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {



        if(meals.getMeals().get(position).getStrMealThumb() != null) {
            Picasso.get().load(meals.getMeals().get(position).getStrMealThumb()).into(holder.mealThumb);
        }
        holder.mealName.setText(meals.getMeals().get(position).getStrMeal());

    }



    @Override
    public int getItemCount() {
        return meals.getMeals().size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mealThumb;
        TextView mealName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealThumb=itemView.findViewById(R.id.mealThumbCat);
            mealName=itemView.findViewById(R.id.mealNameCat);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(getAdapterPosition(),meals.getMeals().get(getAdapterPosition()));
        }
    }




}
