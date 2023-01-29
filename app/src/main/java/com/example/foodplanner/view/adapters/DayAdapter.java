package com.example.foodplanner.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.ModelClasses.DisplayPlanModel;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    Context context;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    List<DisplayPlanModel> itemList;
    PlanMealsAdapter.AdapterConnector adapterConnector;


    public void setItemList(List<DisplayPlanModel> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public DayAdapter(List<DisplayPlanModel> itemList, Context contex, PlanMealsAdapter.AdapterConnector adapterConnector) {
        context = contex;
        this.itemList = itemList;
        this.adapterConnector = adapterConnector;

    }

    @NonNull
    @Override
    public DayAdapter.DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayAdapter.DayViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_days, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {


        DisplayPlanModel parentItem = itemList.get(position);
        holder.textView.setText(parentItem.getnameDay());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        layoutManager.setInitialPrefetchItemCount(parentItem.getListItemPlan().size());
        PlanMealsAdapter planMealsAdapter = new PlanMealsAdapter(parentItem.getListItemPlan(), context, adapterConnector);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(planMealsAdapter);
        holder.recyclerView.setRecycledViewPool(viewPool);
   

    }

    @Override
    public int getItemCount() {
        if (itemList !=null) {
            return itemList.size();
        }else {
            return 0;
        }
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

