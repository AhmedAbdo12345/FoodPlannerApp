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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MealsViewHolder> {
    private MealsModelResponse modelResponse;
    Context context;
     private SearchAdapter.ListItemClickListener mOnClickListener;

    public SearchAdapter(MealsModelResponse mealsModelResponses, Context applicationContext, SearchAdapter.ListItemClickListener mOnClickListener ) {
        this.modelResponse = mealsModelResponses;
        this.context = applicationContext;
        this.mOnClickListener = mOnClickListener;

    }
    public SearchAdapter(MealsModelResponse mealsModelResponses, Context applicationContext) {
        this.modelResponse = mealsModelResponses;
        this.context = applicationContext;

    }

    public interface ListItemClickListener {

        void onClickMeals(int position,List<MealsModelResponse> mealsModelResponses);
    }

    @NonNull
    @Override
    public SearchAdapter.MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchAdapter.MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_meal, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MealsViewHolder holder, int position) {
        if(modelResponse.getMeals().get(0).getStrMealThumb() !=null) {
            Picasso.get().load(modelResponse.getMeals().get(position).getStrMealThumb()).into(holder.imageView);
        }
        holder.textView.setText(modelResponse.getMeals().get(position).getStrMeal());

    }

    @Override
    public int getItemCount() {
        if (modelResponse.getMeals() != null){
            return modelResponse.getMeals().size();

        }else {
            return 0;
        }
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public MealsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.mealCardCat);
            imageView=itemView.findViewById(R.id.mealThumbCat);
            textView=itemView.findViewById(R.id.mealNameCat);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
         //   mOnClickListener.onClickMeals( getAdapterPosition(),modelResponse);

        }

    }

}

