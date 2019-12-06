package com.example.myapplication01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelAdapter;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.skyfishjy.library.RippleBackground;


public class CreareRoleActivity extends AppCompatActivity {

    private WheelView wheelView;
    private ImageView myCha;

    private RippleBackground rippleBackground;

    private Drawable[] drawables;
    private int count;

    private EditText nicknameEdit;
    private TextInputLayout nicknameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_role);


        //ripple api
        rippleBackground=(RippleBackground)findViewById(R.id.content2);
        //edit api
        nicknameEdit = (EditText) findViewById(R.id.et_nickname);
        nicknameLayout = (TextInputLayout) findViewById(R.id.nickname);
        nicknameLayout.setErrorEnabled(true);


        myCha = findViewById(R.id.myCha);
        wheelView = findViewById(R.id.wheelview);
        drawables = new TextDrawable[] { new TextDrawable("Peter"), new TextDrawable("Tina"), new TextDrawable("Scott"), new TextDrawable("James")
                , new TextDrawable("Lily"), new TextDrawable("John"), new TextDrawable("Frank"), new TextDrawable("Marsa")
                , new TextDrawable("Criss"), new TextDrawable("Henry")};



        //populate the adapter, that knows how to draw each item (as you would do with a ListAdapter)
        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public Drawable getDrawable(int position) {
                return drawables[position];
            }

            @Override
            public int getCount() {
                return 10;
            }
        });


        count = 0;
        AnimationDrawable background = (AnimationDrawable)myCha.getBackground();
        // 開始播放
        background.start();

        rippleBackground.startRippleAnimation();


        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectListener() {
            @Override
            public void onWheelItemSelected(WheelView parent,  Drawable itemDrawable, int position) {
                //the adapter position that is closest to the selection angle and it's drawable

                count = position;




                switch (count){
                    case 0:

                        myCha.setBackgroundResource(R.drawable.c1_list);
                        AnimationDrawable background1 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background1.start();
                        break;

                    case 1:
                        myCha.setBackgroundResource(R.drawable.c2_list);
                        AnimationDrawable background2 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background2.start();
                        break;
                    case 2:
                        myCha.setBackgroundResource(R.drawable.c3_list);

                        AnimationDrawable background3 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background3.start();
                        break;

                    case 3:
                        myCha.setBackgroundResource(R.drawable.c4_list);
                        AnimationDrawable background4 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background4.start();
                        break;
                    case 4:
                        myCha.setBackgroundResource(R.drawable.c5_list);
                        AnimationDrawable background5 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background5.start();
                        break;

                    case 5:
                        myCha.setBackgroundResource(R.drawable.c6_list);
                        AnimationDrawable background6 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background6.start();
                        break;
                    case 6:
                        myCha.setBackgroundResource(R.drawable.c7_list);
                        AnimationDrawable background7 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background7.start();
                        break;

                    case 7:
                        myCha.setBackgroundResource(R.drawable.c8_list);
                        AnimationDrawable background8 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background8.start();
                        break;
                    case 8:
                        myCha.setBackgroundResource(R.drawable.c9_list);
                        AnimationDrawable background9 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background9.start();
                        break;

                    case 9:
                        myCha.setBackgroundResource(R.drawable.c10_list);
                        AnimationDrawable background10 = (AnimationDrawable)myCha.getBackground();
                        // 開始播放
                        background10.start();
                        break;
                }




            }
        });

        ShimmerTextView shimmerTextView = findViewById(R.id.shimmer_tv);
        Shimmer shimmer = new Shimmer();
        shimmer.start(shimmerTextView);

    }


    public void check(){
        String nickname = nicknameEdit.getText().toString().trim();
        if (nickname.length()>=1){

        } else {

        }
    }


    public void start(View view) {
        Log.v("scott","go");
    }

    public void goup(View view) {
        count = count - 1;
        if (count == -1){
            count = 9;
        }
        wheelView.setSelected(count);
    }

    public void godown(View view) {
        count = count + 1;
        if (count == 10){
            count = 0;
        }

        wheelView.setSelected(count);

    }
}
