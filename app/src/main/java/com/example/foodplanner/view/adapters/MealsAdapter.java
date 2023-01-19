package com.example.foodplanner.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {
    private List<MealsModelResponse> modelArrayList;
    Context context;
    final private MealsAdapter.ListItemClickListener mOnClickListener;

    public MealsAdapter(List<MealsModelResponse> mealsModelResponses, Context applicationContext, MealsAdapter.ListItemClickListener mOnClickListener ) {
        this.modelArrayList = mealsModelResponses;
        this.context = applicationContext;
        this.mOnClickListener = mOnClickListener;

    }


    public interface ListItemClickListener {

        void onClickMeals(int position,List<MealsModelResponse> mealsModelResponses);
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_random_meals, parent, false));

        }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        if(modelArrayList.get(position).getMeals().get(0).getStrMealThumb() !=null) {
            Picasso.get().load(modelArrayList.get(position).getMeals().get(0).getStrMealThumb()).into(holder.imageView);
        }
        holder.textView.setText(modelArrayList.get(position).getMeals().get(0).getStrMeal());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public MealsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.mealCard);
            imageView=itemView.findViewById(R.id.img_random_Meal);
            textView=itemView.findViewById(R.id.tv_name_random_Meal);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onClickMeals( getAdapterPosition(),modelArrayList);

        }

    }

}
