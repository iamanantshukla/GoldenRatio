package com.example.goldenratiopro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GuideMain extends FragmentActivity {

    private ViewPager2 viewPager;
    private FragmentStateAdapter adapter;
    private int page=3;
    private ImageView dot1,dot2,dot3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_main);



        dot1=findViewById(R.id.dot1);
        dot2=findViewById(R.id.dot2);
        dot3=findViewById(R.id.dot3);



        adapter=new GuideFragmentAdapter(this);

        viewPager=(ViewPager2) findViewById(R.id.guidePager);
        viewPager.setAdapter(adapter);


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    dot1.setImageResource(R.drawable.circlefilled);
                    dot2.setImageResource(R.drawable.circleempty);
                    dot3.setImageResource(R.drawable.circleempty);
                }
                if(position==1){
                    dot2.setImageResource(R.drawable.circlefilled);
                    dot1.setImageResource(R.drawable.circleempty);
                    dot3.setImageResource(R.drawable.circleempty);
                }
                if(position==2){
                    dot2.setImageResource(R.drawable.circleempty);
                    dot1.setImageResource(R.drawable.circleempty);
                    dot3.setImageResource(R.drawable.circlefilled);
                }

                super.onPageSelected(position);
            }
        });



    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }
}