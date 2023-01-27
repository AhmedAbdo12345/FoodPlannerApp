package com.example.foodplanner.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.interfaces.DaySelectedInterface;

public class DayPlanAdapter extends RecyclerView.Adapter<DayPlanAdapter.PlanViewHolder> {
    DaySelectedInterface daySelectedInterface;
    // String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] days;
    Context context;
     //private DayPlanAdapter.ListItemClickListener mOnClickListener;
    public DayPlanAdapter(String[] days, DaySelectedInterface daySelectedInterface) {
        this.days = days;
        this. daySelectedInterface = daySelectedInterface;
    }


  /*  public interface ListItemClickListener {

        void onClickDay(String day, int position);
    }
*/
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
                    holder.radioGroup.setVisibility(View.VISIBLE);
                } else {
                    holder.radioGroup.setVisibility(View.GONE);

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
        SwitchCompat switchCompatDays;
        RadioGroup radioGroup;
        RadioButton radioButtonBreakFast,radioButtonLunch,radioButtonDinner,radioButton;
        Button buttonAddPlan;
        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDays = itemView.findViewById(R.id.tv_days);
            switchCompatDays = itemView.findViewById(R.id.daySwitchCompat);

            radioGroup = itemView.findViewById(R.id.group_radio_type_meals);

            radioButtonBreakFast = itemView.findViewById(R.id.radioButton_BreakFast);
            radioButtonLunch = itemView.findViewById(R.id.radioButton_Lunch);
            radioButtonDinner = itemView.findViewById(R.id.radioButton_Dinner);


           /* radioButtonBreakFast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   // daySelectedInterface.onBreakfastSelected(textViewDays.getText().toString(),isChecked);
                    daySelectedInterface.onPlanSelected(textViewDays.getText().toString(),radioButtonBreakFast.getText().toString(),isChecked);

                }
            });
            radioButtonLunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   // daySelectedInterface.onLunchSelected(textViewDays.getText().toString(),isChecked);
                    daySelectedInterface.onPlanSelected(textViewDays.getText().toString(),radioButtonLunch.getText().toString(),isChecked);

                }
            });
            radioButtonDinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 //   daySelectedInterface.onDinnerSelected(textViewDays.getText().toString(),isChecked);
                    daySelectedInterface.onPlanSelected(textViewDays.getText().toString(),radioButtonDinner.getText().toString(),isChecked);

                }
            });*/
            switchCompatDays.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    daySelectedInterface.onDaySelected(textViewDays.getText().toString(),isChecked);

                }
            });
        }


    }


}

