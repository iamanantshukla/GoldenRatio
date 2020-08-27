package com.example.goldenratiopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FaceRate extends AppCompatActivity {

    private double lenghtratio, WLratio, LipRatio;
    private TextView rateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_rate);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        rateView=findViewById(R.id.textRate);

        WLratio=getIntent().getDoubleExtra("LWratio",0);
        lenghtratio=getIntent().getDoubleExtra("LNratio",0);
        LipRatio=getIntent().getDoubleExtra("LipRatio",0);

        String Rating= String.valueOf(CalculateRating(WLratio, lenghtratio,LipRatio));

        rateView.setText(Rating);

    }

    private int CalculateRating(double wLratio, double lenghtratio, double lipRatio) {

        int rating;
        if(lipRatio>1) {
            rating = (int) (100 - Math.abs((1.618 - wLratio) * 50) - Math.abs((1.618 - lenghtratio) * 50) - Math.abs((1.618 - lipRatio) * 50));
        }
        else{
            rating = (int) (100 - Math.abs((1.618 - wLratio) * 50) - Math.abs((1.618 - lenghtratio) * 50));
        }
        return rating;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FaceRate.this, HomePage.class));
        super.onBackPressed();
    }
}