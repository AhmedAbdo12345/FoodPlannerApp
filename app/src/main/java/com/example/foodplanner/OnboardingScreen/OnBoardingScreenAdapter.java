package com.example.foodplanner.OnboardingScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.foodplanner.R;
import com.example.foodplanner.OnboardingScreen.OnBoardingScreenModel;


import java.util.List;

public class OnBoardingScreenAdapter extends PagerAdapter {

    List<OnBoardingScreenModel> PagesModel;
    Context context;
    LayoutInflater layoutInflater;




    public OnBoardingScreenAdapter(List<OnBoardingScreenModel> pagesModel, Context context) {
        PagesModel = pagesModel;
        this.context = context;
    }

    @Override
    public int getCount() {
        return PagesModel.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboardingscreen,container,false);

        ImageView imageView=view.findViewById(R.id.Page_Icon);
        TextView title=view.findViewById(R.id.Page_Title);
        TextView description=view.findViewById(R.id.Page_description);

        OnBoardingScreenModel onBoardingModel =PagesModel.get(position);

        imageView.setImageResource(onBoardingModel.getImage());
        title.setText(onBoardingModel.getTitle());
        description.setText(onBoardingModel.getDescription());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
