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

public class Nature extends AppCompatActivity {

    private List<ExampleModel> exampleModel;
    private ExampleAdapter exampleAdapter;
    private ViewPager2 viewPager;
    private TextView placeName, placeDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nature);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        String typeArr[]={"Flower Petal", "Seed Heads", "Shell", "Hurricane", "Galaxies"};
        String descArr[]={

                "The number of petals in a flower consistently follows the Fibonacci sequence. Famous examples include the lily, which has three petals, buttercups, which have five, etc...",
                "The head of a flower is also subject to Fibonaccian processes. Typically, seeds are produced at the center, and then migrate towards the outside to fill all the space. Sunflowers provide a great example of these spiraling patterns.",
                "Snail shells and nautilus shells follow the Golden spiral, as does the cochlea of the inner ear. It can also be seen in the horns of certain goats, and the shape of certain spider's webs.",
                "The eye of the storm is like the 0 or 1 in the Fibonacci sequence, as you go on in the counter clockwise spiral you find it increasing at a consistent pattern. This pattern is much like the Golden Ratio.",
                "Spiral galaxies also follow the familiar Fibonacci pattern. The Milky Way has several spiral arms, each of them a logarithmic spiral of about 12 degrees."


        };

        int scrollImage[]={R.drawable.rome_golden, R.drawable.pyramid_goldenratio, 0, R.drawable.tajmahal_golden};

        placeDesc=findViewById(R.id.typedesc);
        placeName=findViewById(R.id.typename);







        exampleModel=new ArrayList<>();
        exampleModel.add(new ExampleModel(R.drawable.nature_flower));
        exampleModel.add(new ExampleModel(R.drawable.nature_seedheads));
        exampleModel.add(new ExampleModel(R.drawable.nature_shell));
        exampleModel.add(new ExampleModel(R.drawable.nature_hurricane));
        exampleModel.add(new ExampleModel(R.drawable.nature_galaxy));



        exampleAdapter = new ExampleAdapter(exampleModel);

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

        placeDesc.setAnimation(fadeIn);
        placeDesc.setAnimation(fadeout);


        viewPager = findViewById(R.id.typeExample);
        viewPager.setAdapter(exampleAdapter);
        //viewPager.setPageTransformer(new ZoomOutPageTransformer());

        viewPager.setPadding(135,144,135,72);

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
                placeDesc.startAnimation(fadeout);

                Handler mhandler = new Handler();
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        placeName.setText(typeArr[position]);
                        placeDesc.setText(descArr[position]);

                        placeName.startAnimation(fadeIn);
                        placeDesc.startAnimation(fadeIn);

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