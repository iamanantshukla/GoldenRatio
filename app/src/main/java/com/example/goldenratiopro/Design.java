package com.example.goldenratiopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Design extends AppCompatActivity {

    private List<ExampleModel> exampleModel;
    private DesignAdapter designAdapter;
    private ViewPager2 viewPager;
    private TextView placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        String typeArr[]={"Apple", "Twitter", "Pepsi", "Toyota", "Google"};





        placeName=findViewById(R.id.typename);







        exampleModel=new ArrayList<>();
        exampleModel.add(new ExampleModel(R.drawable.logo_apple));
        exampleModel.add(new ExampleModel(R.drawable.logo_twiter));
        exampleModel.add(new ExampleModel(R.drawable.logo_pepsi));
        exampleModel.add(new ExampleModel(R.drawable.logo_toyota));
        exampleModel.add(new ExampleModel(R.drawable.logo_google));



        designAdapter = new DesignAdapter(exampleModel);

        AlphaAnimation fadeout= new AlphaAnimation(1f,0f);
        fadeout.setFillAfter(true);
        fadeout.setDuration(200);
        fadeout.setStartOffset(0);
        placeName.setAnimation(fadeout);

        AlphaAnimation fadeIn= new AlphaAnimation(0f,1f);
        fadeIn.setFillAfter(true);
        fadeIn.setDuration(200);
        fadeIn.setStartOffset(0);

        placeName.setAnimation(fadeIn);


        viewPager = findViewById(R.id.typeExample);
        viewPager.setAdapter(designAdapter);
        //viewPager.setPageTransformer(new ZoomOutPageTransformer());

        viewPager.setPadding(120,144,120,72);

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {

                placeName.startAnimation(fadeout);


                Handler mhandler = new Handler();
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        placeName.setText(typeArr[position]);


                        placeName.startAnimation(fadeIn);


                    }
                },500);



                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

}