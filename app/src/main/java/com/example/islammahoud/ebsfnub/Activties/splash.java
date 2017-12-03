package com.example.islammahoud.ebsfnub.Activties;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.islammahoud.ebsfnub.R;

public class splash extends AppCompatActivity {

    private final static int  SPLASH_DISPLAY_LENGTH =3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView view = (ImageView) findViewById(R.id.splash); //Initialize ImageView via FindViewById or programatically


        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
//Setup anim with desired properties
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(0); //Repeat animation indefinitely
        anim.setDuration(1000); //Put desired duration per anim cycle here, in milliseconds
//Start animation
        view.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this, MainActivity.class);
                splash.this.startActivity(intent);
                splash.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
