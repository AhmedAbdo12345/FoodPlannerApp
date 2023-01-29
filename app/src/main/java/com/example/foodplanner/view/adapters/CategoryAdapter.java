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
import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MealsViewHolder> {
    private CategoryModelResponse categoryModelResponses;
    Context context;
    final private CategoryAdapter.ListItemClickListener mOnClickListener;

    public CategoryAdapter(CategoryModelResponse categoryModelResponses, Context applicationContext, CategoryAdapter.ListItemClickListener mOnClickListener ) {
        this.categoryModelResponses = categoryModelResponses;
        this.context = applicationContext;
        this.mOnClickListener = mOnClickListener;
    }


    public interface ListItemClickListener {
        void onClickCategory(int position,String nameCategory);
    }

    @NonNull
    @Override

    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_categories, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        if(categoryModelResponses.getCategories().get(position).getStrCategoryThumb() != null) {
            Picasso.get().load(categoryModelResponses.getCategories().get(position).getStrCategoryThumb()).into(holder.imageView);
        }
        holder.textView.setText(categoryModelResponses.getCategories().get(position).getStrCategory());

    }

    @Override
    public int getItemCount() {
        return categoryModelResponses.getCategories().size();
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
          CardView cardView;

        public MealsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.categoryCard);

            imageView=itemView.findViewById(R.id.img_category);
            textView=itemView.findViewById(R.id.tv_name_category);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClickCategory( getAdapterPosition(),categoryModelResponses
                    .getCategories().get(getAdapterPosition()).getStrCategory());

        }
    }
}

