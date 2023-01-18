package com.example.foodplanner.OnboardingScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.view.activities.AuthActivity;
import com.example.foodplanner.view.activities.HomeActivity;

import java.util.List;

public class OnboardingScreenActivity extends AppCompatActivity {
    ViewPager viewPager;
    List<OnBoardingScreenModel> onBoardingModels;
    PagerAdapter pagerAdapter;
    LinearLayout linearLayout;
    TextView[] dots;
    Button GetStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);


        viewPager =findViewById(R.id.OnBoardingPageView);
        onBoardingModels = OnBoardingScreenModelResponse.getOnBoardingObjects();
        pagerAdapter =new OnBoardingScreenAdapter(onBoardingModels,this);
        viewPager.setAdapter(pagerAdapter);

        linearLayout=findViewById(R.id.dots_Slider);
        add_dotsSlider(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                add_dotsSlider( position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getStartedButton();
    }

    private void getStartedButton() {
        GetStarted=findViewById(R.id.btn_signup);
        final Intent intent=new Intent(this, AuthActivity.class);
        GetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScreen();
                startActivity(intent);
            }
        });
    }

    private void add_dotsSlider(int position) {
        linearLayout.removeAllViews();
        dots=new TextView[onBoardingModels.size()];
        for (int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(getResources().getColor(R.color.white));
            linearLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.grey));

    }

    private void updateScreen() {
        SharedPreferences Shared=getSharedPreferences(DeciderActivity.MYPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=Shared.edit();
        editor.putBoolean("seen",true);
        editor.commit();
    }

}