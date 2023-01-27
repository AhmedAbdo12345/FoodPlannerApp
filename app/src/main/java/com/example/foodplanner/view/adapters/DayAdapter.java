package com.example.foodplanner.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.plan.PlanMealsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private ArrayList<String> listUniqDays;
    List<PlanMealsModel> newList=new ArrayList<>();

    List<PlanMealsModel> planMealsModelList;
    Context context;
    PlanMealsAdapter.ListItemClickListener mOnClickListener;

    public DayAdapter(ArrayList<String> listUniqDays,List<PlanMealsModel> planMealsModelList, Context applicationContext,PlanMealsAdapter.ListItemClickListener mOnClickListener ) {
        this.listUniqDays = listUniqDays;
        this.context = applicationContext;
        this.planMealsModelList=planMealsModelList;
        this.mOnClickListener=mOnClickListener;

    }


    @NonNull
    @Override
    public DayAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayAdapter.DayViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_days, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.DayViewHolder holder, int position) {
        newList.clear();
        holder.textView.setText(listUniqDays.get(position));
       holder.recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        holder.recyclerView.setLayoutManager(gridLayoutManager);

        for(int i=0;i<planMealsModelList.size();i++){
            if (planMealsModelList.get(i).getDay().equals( listUniqDays.get(position))){
                newList.add(planMealsModelList.get(i));
            }
        }
        Log.i("zxc", "onBindViewHolder: "+newList);
        PlanMealsAdapter  planMealsAdapter = new PlanMealsAdapter(newList,context,listUniqDays,mOnClickListener);

        holder.recyclerView.setAdapter(planMealsAdapter);
   

    }

    @Override
    public int getItemCount() {
        return listUniqDays.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;
        RecyclerView recyclerView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_plan_days);
            recyclerView=itemView.findViewById(R.id.recycler_view_day_Plan_Meals);
        }



    }

}

