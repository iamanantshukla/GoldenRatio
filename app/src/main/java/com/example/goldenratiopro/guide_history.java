package com.example.goldenratiopro;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
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

public class guide_history extends Fragment {

    private ImageView greekGod, colontop, colonbtm, linebar, mainBack;
    private TextView para1,para2,mainHead,openHead,textDef;


    public guide_history() {
        // Required empty public constructor
    }

    public static guide_history newInstance(String param1, String param2) {
        guide_history history = new guide_history();

        return history;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_guide_history,container,false);

        greekGod=view.findViewById(R.id.greekman);
        colontop=view.findViewById(R.id.colontop);
        colonbtm=view.findViewById(R.id.colonbtm);
        mainBack=view.findViewById(R.id.mainBack);
        linebar=view.findViewById(R.id.linebar);

        para1=view.findViewById(R.id.mpara1);
        para2=view.findViewById(R.id.mpara2);
        textDef=view.findViewById(R.id.textDef);
        mainHead=view.findViewById(R.id.mmainHead);
        openHead=view.findViewById(R.id.mopenHead);

        greekGod.setVisibility(View.VISIBLE);
        openHead.setVisibility(View.VISIBLE);

        greekGod.startAnimation(AnimationUtils.loadAnimation(getContext(),fragment_guide));

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1f);
        alphaAnimation.setDuration(1200);
        alphaAnimation.setStartOffset(0);
        alphaAnimation.setFillAfter(true);



        //openHead animation
        openHead.setTranslationY(-200f);
        ObjectAnimator animation = ObjectAnimator.ofFloat(openHead, "translationY", 0f);
        animation.setDuration(1200);
        animation.start();


        openHead.setAnimation(alphaAnimation);

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

                ObjectAnimator outgodfade= ObjectAnimator.ofFloat(greekGod,"alpha",1f,0f);
                outgodfade.setDuration(1200);
                outgodfade.start();

                greekGod.startAnimation(AnimationUtils.loadAnimation(getContext(), frag_imageout));

                mainBack.setVisibility(View.VISIBLE);
                Animation fragment_back= AnimationUtils.loadAnimation(getContext(), fragment_guide);
                mainBack.startAnimation(fragment_back);
            }
        },1600);

        Handler mHandler= new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainHead.setVisibility(View.VISIBLE);
                para1.setVisibility(View.VISIBLE);
                para2.setVisibility(View.VISIBLE);
                linebar.setVisibility(View.VISIBLE);
                textDef.setVisibility(View.VISIBLE);
                colontop.setVisibility(View.VISIBLE);
                colonbtm.setVisibility(View.VISIBLE);


                Animation fragment_anim= AnimationUtils.loadAnimation(getContext(), fragment_guide);
                mainHead.startAnimation(fragment_anim);
                para1.startAnimation(fragment_anim);
                para2.startAnimation(fragment_anim);
                linebar.startAnimation(fragment_anim);
                textDef.startAnimation(fragment_anim);
                colonbtm.startAnimation(fragment_anim);
                colontop.startAnimation(fragment_anim);




            }
        },2000);




        return view;

    }


}