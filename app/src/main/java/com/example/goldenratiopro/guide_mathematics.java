package com.example.goldenratiopro;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.goldenratiopro.R.anim.frag_imageout;
import static com.example.goldenratiopro.R.anim.fragment_guide;


public class guide_mathematics extends Fragment {

    private TextView mainHead,openHead, para1, para2;
    private ImageView openImage, linebar, segment, formula, value;


    public guide_mathematics() {
        // Required empty public constructor
    }

    public static guide_mathematics newInstance(String param1, String param2) {
        guide_mathematics mathematics = new guide_mathematics();
        return mathematics;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_guide_mathematics, container, false);

        openImage=view.findViewById(R.id.mopenImage);
        openHead=view.findViewById(R.id.mopenHead);
        mainHead=view.findViewById(R.id.mmainHead);
        linebar=view.findViewById(R.id.mlineBar);
        formula=view.findViewById(R.id.mformula);
        value=view.findViewById(R.id.mvalue);
        segment=view.findViewById(R.id.msegmentImage);
        para1=view.findViewById(R.id.mpara1);
        para2=view.findViewById(R.id.mpara2);

        openImage.setVisibility(View.INVISIBLE);
        openHead.setVisibility(View.INVISIBLE);

        Handler oHandler= new Handler();
        oHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                openImage.setVisibility(View.VISIBLE);
                openHead.setVisibility(View.VISIBLE);

                openImage.startAnimation(AnimationUtils.loadAnimation(getContext(),fragment_guide));

                AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1f);
                alphaAnimation.setDuration(1500);
                alphaAnimation.setStartOffset(0);
                alphaAnimation.setFillAfter(true);



                //openHead animation
                openHead.setTranslationY(-200f);
                ObjectAnimator animation = ObjectAnimator.ofFloat(openHead, "translationY", 0f);
                animation.setDuration(1500);
                animation.start();


                openHead.setAnimation(alphaAnimation);
            }
        },200);


        Handler hHandler= new Handler();
        hHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator outanimation = ObjectAnimator.ofFloat(openHead, "translationY", 200f);
                outanimation.setDuration(1200);
                outanimation.start();

                ObjectAnimator outfade= ObjectAnimator.ofFloat(openHead,"alpha",1f,0f);
                outfade.setDuration(1200);
                outfade.start();

                ObjectAnimator outgodfade= ObjectAnimator.ofFloat(openImage,"alpha",1f,0f);
                outgodfade.setDuration(1200);
                outgodfade.start();

                openImage.startAnimation(AnimationUtils.loadAnimation(getContext(), frag_imageout));

            }


        },1700);

        Handler mHandler= new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainHead.setVisibility(View.VISIBLE);
                para1.setVisibility(View.VISIBLE);
                para2.setVisibility(View.VISIBLE);
                linebar.setVisibility(View.VISIBLE);
                formula.setVisibility(View.VISIBLE);
                segment.setVisibility(View.VISIBLE);
                value.setVisibility(View.VISIBLE);



                Animation fragment_anim= AnimationUtils.loadAnimation(getContext(), fragment_guide);
                mainHead.startAnimation(fragment_anim);
                para1.startAnimation(fragment_anim);
                formula.startAnimation(fragment_anim);
                linebar.startAnimation(fragment_anim);
                segment.startAnimation(fragment_anim);
                value.startAnimation(fragment_anim);
                para2.startAnimation(fragment_anim);





            }
        },2000);




        return view;
    }
}