package com.example.foodplanner.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelResponse.MealsModelResponse;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {
    private ArrayList<MealsModelResponse> modelArrayList = new ArrayList<>();

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_meals, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView textView;
        public MealsViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
