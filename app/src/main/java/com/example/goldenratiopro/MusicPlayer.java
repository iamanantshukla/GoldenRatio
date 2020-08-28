package com.example.goldenratiopro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.tankery.lib.circularseekbar.CircularSeekBar;




public class MusicPlayer extends AppCompatActivity {

    private CircularSeekBar circularSeekBar;
    private MediaPlayer mediaPlayer;
    private ImageView playPause;
    private int playlist=0;
    int theplayer=0;
    int length;
    boolean currentState=true;
    AudioManager audioManager;
    private TextView songName, artistName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        songName=findViewById(R.id.songName);
        artistName=findViewById(R.id.artistName);

        circularSeekBar = findViewById(R.id.Seekbar);
        playPause= findViewById(R.id.playPause);


        playsong(playlist);
}
    //setting up different music
    private void playsong(int playlist) {

        if(playlist==0) {

            audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.symphony);
            songName.setText("5th Symphony");
            artistName.setText("Ludwig van Beethoven");

        }
        if(playlist==1){
            audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.genisis);
            songName.setText("Genesis");
            artistName.setText("Firth of Fifth");
        }

        if(playlist==2){
            audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.deeppurple);
            songName.setText("Child in Time");
            artistName.setText("Deep Purple");
        }


            final Handler mhandler = new Handler();


            int maxPosition = mediaPlayer.getDuration();
            circularSeekBar.setMax(maxPosition);

            mediaPlayer.start();

            MusicPlayer.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    circularSeekBar.setProgress(currentPosition);
                    mhandler.postDelayed(this, 100);
                }

            });


            circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
                @Override
                public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                    if(fromUser) {
                        mediaPlayer.seekTo((int) progress);
                    }
                }

                @Override
                public void onStopTrackingTouch(CircularSeekBar seekBar) {

                }

                @Override
                public void onStartTrackingTouch(CircularSeekBar seekBar) {

                }
            });

    }

    //Play song method

    public void play(View view){

        if(currentState==false) {

            playPause.setImageResource(R.mipmap.pause);

            if (theplayer == 0) {
                mediaPlayer.start();

                currentState = true;
            } else {
                mediaPlayer.seekTo(length);
                mediaPlayer.start();
                currentState=true;
            }
        }
        else{
            mediaPlayer.pause();
            playPause.setImageResource(R.mipmap.play);
            theplayer=1;
            length=mediaPlayer.getCurrentPosition();
            currentState=false;
        }


    }

    //next song

    public void next(View view){
        if(playlist==0) {
            playlist++;
            mediaPlayer.stop();
            playsong(playlist);

        }

        else if(playlist==1) {
            playlist++;
            mediaPlayer.stop();
            playsong(playlist);

        }
    }

    //previous song
    public void previous(View view){
        if(playlist==0){
            Toast.makeText(getApplicationContext(), "This is the First Song", Toast.LENGTH_LONG).show();
        }
        else if(playlist==1){
            playlist--;
            mediaPlayer.stop();
            playsong(playlist);
        }
        else if(playlist==2){
            playlist--;
            mediaPlayer.stop();
            playsong(playlist);
        }
    }

    //finish music activity to stop playing music in background
    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        finish();
        super.onBackPressed();
    }



}
