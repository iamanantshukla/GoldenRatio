package com.example.goldenratiopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Human extends AppCompatActivity {

    Dialog cardDialog;
    ImageView dot_face,dot_hand,dot_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        dot_face=findViewById(R.id.dot_face);
        dot_hand=findViewById(R.id.dot_hand);

        dot_body=findViewById(R.id.dot_body);


        cardDialog= new Dialog(this);
    }

    public void popupDialog(View view){
        TextView hc_text;
        ImageView hc_image , hc_cancel;

        if(view==dot_body){
            cardDialog.setContentView(R.layout.humanbody_card);
            hc_cancel=cardDialog.findViewById(R.id.hc_cancel2);
        }
        else {
            cardDialog.setContentView(R.layout.human_card);
            hc_text = cardDialog.findViewById(R.id.hc_text);
            hc_image = cardDialog.findViewById(R.id.hc_image);
            hc_cancel = cardDialog.findViewById(R.id.hc_cancel);

            if (view == dot_face) {
                hc_image.setImageResource(R.drawable.face_golden);
                hc_text.setText("The head forms a golden rectangle with the eyes at its midpoint. The mouth and nose are each placed at golden sections of the distance between the eyes and the bottom of the chin. The beauty unfolds as you look further.");
            }
            if (view == dot_hand) {
                hc_image.setImageResource(R.drawable.hand_golden);
                hc_text.setText("Each section of our index finger, from the tip to the base of the wrist, is larger than the preceding one by about the Fibonacci ratio.\nOur hand creates a golden section in relation to our arm, as the ratio of our forearm to our hand is also 1.618, the Divine Proportion.");
            }

        }
        hc_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDialog.dismiss();
            }
        });
        cardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cardDialog.getWindow().setWindowAnimations(R.style.pop_up);
        cardDialog.show();

    }
}