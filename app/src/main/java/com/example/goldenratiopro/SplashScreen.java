package com.example.goldenratiopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private ImageView appName, appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        appLogo=findViewById(R.id.appLogo);
        appName=findViewById(R.id.appName);

        AlphaAnimation alphaAnimation= new AlphaAnimation(0f, 1f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);

        appName.setAnimation(alphaAnimation);
        appLogo.setAnimation(alphaAnimation);

        alphaAnimation.start();

        overridePendingTransition(R.anim.fadein,R.anim.splashscreenout);

        Handler mhandler= new Handler();
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, HomePage.class));
            }
        }, 2000);

    }
}