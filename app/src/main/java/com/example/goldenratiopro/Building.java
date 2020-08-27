package com.example.goldenratiopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Building extends AppCompatActivity {
    List<ExampleModel> exampleModel;
    ExampleAdapter exampleAdapter;
    ViewPager2 viewPager;
    TextView placeName, placeDesc;
    ImageView imageView;
    TextSwitcher textSwitcher;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        String placenameArr[]={"Parthenon, Rome", "Pyramid of Giza, Egypt", "Chichen Itza, Mexico", "Taj Mahal, Agra"};
        String descnameArr[]={

                "The Parthenon was built in the 5th century B.C. when the Athenian Empire was influential and models the power and supremacy of the empire.  It was dedicated to the Greek goddess Athena.  The temple was constructed by three architects during Iktinus, Callicrates, and Phidias.  The symbol for the golden ratio, the Greek letter phi-, was named after the sculptor Phidias.  The golden ratio appears in several constructions and layouts of the Parthenon. ",
                "The most famous monuments of ancient Egypt are the Great Pyramids of Giza.  Believed to have been constructed around 4,600 years ago, these pyramids were built around the golden ratio, long before the Greeks and the Parthenon.  The largest of the pyramids in Giza contains the use of phi and the golden ratio.  The golden ratio is represented as the ratio of the length/height of the triangular face to half the length of the square base. ",
                "The Castle of Chichen Itza was built by the Maya civilization between the 11th and 13th centuries AD as a temple to the god Kukulcan. John Pile claims that its interior layout has golden ratio proportions. He says that the interior walls are placed so that the outer spaces are related to the central chamber by the golden ratio.",
                "The Taj Mahal was built by the Mughal emperor Shah Jahān (reigned 1628–58) to immortalize his wife Mumtaz Mahal. The Taj Mahal displays golden proportions in the width of its grand central arch to its width, and also in the height of the windows inside the arch to the height of the main section below the domes."

        };

        int scrollImage[]={R.drawable.rome_golden, R.drawable.pyramid_goldenratio, 0, R.drawable.tajmahal_golden};

        placeDesc=findViewById(R.id.typedesc);
        placeName=findViewById(R.id.typename);
        imageView=findViewById(R.id.scrollImage);






        exampleModel=new ArrayList<>();
        exampleModel.add(new ExampleModel(R.drawable.parthenon_rome));
        exampleModel.add(new ExampleModel(R.drawable.pyramidofgiza));
        exampleModel.add(new ExampleModel(R.drawable.chichenitza));
        exampleModel.add(new ExampleModel(R.drawable.tajmahal_agra));
        


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

        imageView.setAnimation(fadeIn);
        imageView.setAnimation(fadeout);

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
                imageView.startAnimation(fadeout);
                Handler mhandler = new Handler();
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        placeName.setText(placenameArr[position]);
                        placeDesc.setText(descnameArr[position]);
                        imageView.setImageResource(scrollImage[position]);
                        placeName.startAnimation(fadeIn);
                        placeDesc.startAnimation(fadeIn);
                        imageView.startAnimation(fadeIn);
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