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

import com.airbnb.lottie.LottieAnimationView;

import static com.example.goldenratiopro.R.anim.frag_imageout;
import static com.example.goldenratiopro.R.anim.fragment_guide;

public class guide_geometry extends Fragment {

    private ImageView openImage, lineBar, mainImage;
    private TextView openHead,para1,mainHead;
    private LottieAnimationView lottieAnimationView;

    public guide_geometry() {
        // Required empty public constructor
    }

    public static Fragment newInstance(String s, String mathematics) {
        guide_geometry guide_geometry = new guide_geometry();

        return guide_geometry;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_guide_geometry, container, false);

        openImage=view.findViewById(R.id.gopenImage);
        openHead=view.findViewById(R.id.gopenhead);
        lottieAnimationView=view.findViewById(R.id.ganim);
        mainHead=view.findViewById(R.id.gmainHead);
        lineBar=view.findViewById(R.id.glineBar);
        mainImage=view.findViewById(R.id.gmainImage);
        para1=view.findViewById(R.id.gpara1);

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
                lottieAnimationView.playAnimation();
                mainHead.setVisibility(View.VISIBLE);
                para1.setVisibility(View.VISIBLE);
                lineBar.setVisibility(View.VISIBLE);
                mainImage.setVisibility(View.VISIBLE);



                Animation fragment_anim= AnimationUtils.loadAnimation(getContext(), fragment_guide);
                mainHead.startAnimation(fragment_anim);
                para1.startAnimation(fragment_anim);
                mainImage.startAnimation(fragment_anim);
                lineBar.startAnimation(fragment_anim);

            }
        },2000);


        return view;
    }
}