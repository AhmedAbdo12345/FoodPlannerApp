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
import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MealByCategoryAdapter extends RecyclerView.Adapter<MealByCategoryAdapter.RecyclerViewHolder> {
    private CategoryModelResponse categoryModelResponses;
    private MealsModelResponse meals;
    private Context context;
    private static ClickListener clickListener;

    public MealByCategoryAdapter(Context context,MealsModelResponse meals) {
        this.meals = meals;
        this.context = context;
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
        @BindView(R.id.mealThumbCat)
        ImageView mealThumb;
        @BindView(R.id.mealNameCat)
        TextView mealName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        MealByCategoryAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
