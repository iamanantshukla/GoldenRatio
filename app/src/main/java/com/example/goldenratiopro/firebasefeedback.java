package com.example.goldenratiopro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class firebasefeedback extends AppCompatActivity {

    private TextView Name, Subject, Desc;
    private Button send;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        final ProgressBar loading= findViewById(R.id.progressBar);
        Name= findViewById(R.id.nameText);
        Subject=findViewById(R.id.subjectText);
        Desc=findViewById(R.id.descText);

        send=findViewById(R.id.sendBtn);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Name.getText().toString().isEmpty() || Subject.getText().toString().isEmpty() || Desc.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_LONG).show();

                }
                else{
                    loading.setVisibility(View.VISIBLE);

                    HashMap<String, String> map= new HashMap<>();
                    map.put("Subject", Subject.getText().toString());
                    map.put("Description", Desc.getText().toString());

                    reference.child("Feedback").child(Name.getText().toString()).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            loading.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Feedback Received", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(firebasefeedback.this, HomePage.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            loading.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Unable to send Feedback", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(firebasefeedback.this, HomePage.class));

                        }
                    });




                }
            }
        });



    }
}