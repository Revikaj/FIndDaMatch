//Welcome screen displays game name and creators, before going to Main Menu screen. Includes some animations
package com.example.cmpt276project.GameGeneral.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cmpt276project.R;

public class WelcomeScreen extends AppCompatActivity {
    //used in the welcome screen animation
    ObjectAnimator animation1, animation2,animation3;
    private ImageView imgview;

    //The application starts from this screen and then navigates to the main menu after a couple seconds on its own.
    private static int TIME=8000;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);
        imgview=findViewById(R.id.cloche);
        animate();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() { nextActivity();
            }
        },TIME);
        setUpSkipButton();

    }


    private void setUpSkipButton() {
       Button Skip = findViewById(R.id.skipbtn);
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipSplash();
            }
        });
    }

    private void skipSplash() {
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
        nextActivity();
    }

    private void nextActivity() {
        Intent welcomeIntent = new Intent(WelcomeScreen.this, MainActivity.class);
        startActivity(welcomeIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    //Animation for the movement of the cloche
    public void animate(){
        animation1= ObjectAnimator.ofFloat(imgview,"x",400f,700f);
        animation3=ObjectAnimator.ofFloat(imgview,"y",600f,500f);
        animation2=ObjectAnimator.ofFloat(imgview,"rotation",0f,60f);
        animation2.setDuration(5000);
        animation1.setDuration(5000);
        animation3.setDuration(5000);
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animation1,animation2,animation3);
        animation1.start();
        animation2.start();
        animation3.start();
    }
}