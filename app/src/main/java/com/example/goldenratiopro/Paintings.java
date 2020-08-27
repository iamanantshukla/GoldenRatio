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

public class Paintings extends AppCompatActivity {

    private List<ExampleModel> exampleModel;
    private ExampleAdapter exampleAdapter;
    private ViewPager2 viewPager;
    private TextView placeName, placeDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paintings);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        String typeArr[]={"The Last Supper", "The Creation of Adam", "The Birth of Venus", "Bathers at Asnières", "Sacrament of the Last Supper"};
        String descArr[]={

                "The Golden Section was used extensively by Leonardo Da Vinci. All the key dimensions of the room, the table and ornamental shields in Da Vinci’s “The Last Supper” were based on the Golden Ratio, which was known in the Renaissance period as The Divine Proportion.",
                "In Michelangelo’s painting of “The Creation of Adam” on the ceiling of the Sistine Chapel, the finger of God touches the finger of Adam precisely at the golden ratio point of the width and height of the area that contains them both.",
                "In the Bottocelli composed “The Birth of Venus”, Venus's navel is at the golden ratio of her height, as well as the height of the painting itself.",
                "Seurat attacked every canvas with the golden ratio. For the creation of his painting Bathers at Asnières, the compositional lines suggest a conscious decision to use the golden ratio as well as the rule of thirds.",
                "In the Sacrament of the Last Supper, Salvador Dali positioned the table exactly at the golden section of the height of his painting. His entire painting is in fact framed in a golden rectangle and he didn’t stop there. The positioning of the two disciples at Christ’s side, Dali placed at the golden sections of the width of the composition."

        };


        placeDesc=findViewById(R.id.typedesc);
        placeName=findViewById(R.id.typename);







        exampleModel=new ArrayList<>();
        exampleModel.add(new ExampleModel(R.drawable.art_lastsupper));
        exampleModel.add(new ExampleModel(R.drawable.art_creationofadams));
        exampleModel.add(new ExampleModel(R.drawable.art_nascita));
        exampleModel.add(new ExampleModel(R.drawable.art_seuret));
        exampleModel.add(new ExampleModel(R.drawable.art_salvadordali));



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