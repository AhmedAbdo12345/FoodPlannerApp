package com.example.foodplanner.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelResponse.CategoryModelResponse;
import com.squareup.picasso.Picasso;

public class DayPlanAdapter extends RecyclerView.Adapter<DayPlanAdapter.PlanViewHolder> {
   // String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] days;
    public DayPlanAdapter(String[] days) {
        this.days = days;
    }

    @NonNull
    @Override
    public DayPlanAdapter.PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayPlanAdapter.PlanViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_day_cardview, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DayPlanAdapter.PlanViewHolder holder, int position) {
        holder.textViewDays.setText(days[position]);

        holder.switchCompatDays.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.linearLayoutMeals.setVisibility(View.VISIBLE);
                } else {
                    holder.linearLayoutMeals.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class PlanViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDays;
        SwitchCompat switchCompatDays, switchCompatBreakFast, switchCompatLunch, switchCompatDinner;
        LinearLayout linearLayoutMeals;

        public PlanViewHolder(@NonNull View itemView) {
            //row
            super(itemView);
            textViewDays = itemView.findViewById(R.id.tv_days);
            switchCompatBreakFast = itemView.findViewById(R.id.breakfastSwitchCompat);
            switchCompatLunch = itemView.findViewById(R.id.lunchSwitchCompat);
            switchCompatDinner = itemView.findViewById(R.id.dinnerSwitchCompat);
            linearLayoutMeals = itemView.findViewById(R.id.type_meals_layout);

            switchCompatDays = itemView.findViewById(R.id.daySwitchCompat);

        }
    }
}

