package com.example.myapplication01;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.skyfishjy.library.RippleBackground;

public class WelActivity extends AppCompatActivity {


    private RippleBackground rippleBackground;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);

        rippleBackground = findViewById(R.id.content);
        imageView = findViewById(R.id.centerImage);

        rippleBackground.startRippleAnimation();



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(WelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        ObjectAnimator animation = ObjectAnimator.ofFloat(imageView,"alpha",1,0,1);
        animation.setRepeatCount(-1);
        animation.setDuration(3000);
        animation.start();

    }
}
