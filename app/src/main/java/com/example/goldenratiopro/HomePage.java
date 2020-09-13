package com.example.goldenratiopro;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements HomePageAdapter.OnNoteListener {

    List<HomeModel> homeModel;
    HomePageAdapter homePageAdapter;
    ViewPager2 viewPager;
    ImageView guide,faceRate,optionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        //Feedback Drop Down without Action Bar

        optionMenu=findViewById(R.id.option);
        PopupMenu dropdownMenu= new PopupMenu(getApplicationContext(), optionMenu);
        Menu menu = dropdownMenu.getMenu();

        menu.add(0,0,0,"Send Feedback");

        dropdownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==0){
                    startActivity(new Intent(HomePage.this, firebasefeedback.class));
                }
                return false;
            }
        });

        optionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownMenu.show();
            }
        });



        guide=findViewById(R.id.imageGuide);

        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(HomePage.this, GuideMain.class);
                startActivity(i);
            }
        });

        faceRate=findViewById(R.id.imageRate);

        faceRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(HomePage.this, MainActivity.class);
                startActivity(i);
            }
        });

        CardView cardView=findViewById(R.id.cardView);
        cardView.setElevation(0f);


        homeModel=new ArrayList<>();
        homeModel.add(new HomeModel(R.mipmap.music));
        homeModel.add(new HomeModel(R.mipmap.human));
        homeModel.add(new HomeModel(R.mipmap.nature));
        homeModel.add(new HomeModel(R.mipmap.painting));
        homeModel.add(new HomeModel(R.mipmap.logo));
        homeModel.add(new HomeModel(R.mipmap.building));


        homePageAdapter = new HomePageAdapter(homeModel, this);


        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(homePageAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

        viewPager.setPadding(230,0,230,0);

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);





    }

    @Override
    public void OnNoteClick(int position) {

        if(position==0){
            startActivity(new Intent(HomePage.this, MusicPlayer.class));
        }
        if(position==5){
            startActivity(new Intent(HomePage.this, Building.class));
        }
        if(position==1){
            startActivity(new Intent(HomePage.this, Human.class));
        }
        if(position==2){
            startActivity(new Intent(HomePage.this, Nature.class));
        }
        if(position==3){
            startActivity(new Intent(HomePage.this, Paintings.class));
        }
        if(position==4){
            startActivity(new Intent(HomePage.this, Design.class));
        }

    }




}