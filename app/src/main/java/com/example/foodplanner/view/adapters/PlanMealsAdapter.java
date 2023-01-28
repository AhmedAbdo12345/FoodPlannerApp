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
import com.example.foodplanner.model.database.plan.PlanMealsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlanMealsAdapter extends RecyclerView.Adapter<PlanMealsAdapter.PlanViewHolder> {
    private List<PlanMealsModel> planMealsModelList;
    Context context;
    ArrayList<String> listUniqDays;
    final private PlanMealsAdapter.ListItemClickListener mOnClickListener;

    public PlanMealsAdapter(List<PlanMealsModel> planMealsModelList, Context context, ArrayList<String> listUniqDays,PlanMealsAdapter.ListItemClickListener mOnClickListener) {
        this.planMealsModelList = planMealsModelList;
        this.context = context;
        this.listUniqDays=listUniqDays;
        this.mOnClickListener = mOnClickListener;
    }


    public interface ListItemClickListener {
        void onClick(int position, PlanMealsModel planMealsModel);
    }


    @NonNull
    @Override
    public PlanMealsAdapter.PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanMealsAdapter.PlanViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_plan_meals, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PlanMealsAdapter.PlanViewHolder holder, int position) {

            if (planMealsModelList.get(position).getStrMealThumb() != null) {
                Picasso.get().load(planMealsModelList.get(position).getStrMealThumb()).into(holder.imgMeal);
            }
            holder.textViewTitle.setText(planMealsModelList.get(position).getStrMeal());

    }

    @Override
    public int getItemCount() {
        if (planMealsModelList != null){
            return planMealsModelList.size();

        }else {
            return 0;
        }
    }

    public class PlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgMeal,imgDelte;
        TextView textViewTitle;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.img_plan_meals);
            textViewTitle = itemView.findViewById(R.id.tv_plan_title_meals);
            imgDelte = itemView.findViewById(R.id.img_delete_plsn_meal);
            imgDelte.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            mOnClickListener.onClick( getAdapterPosition(),planMealsModelList.get(getAdapterPosition()));

        }
    }

}
