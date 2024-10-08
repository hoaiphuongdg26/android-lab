package com.example.lab3_animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode,
            btnBlinkXml,
            btnBlinkCode, btnZoomInXml, btnZoomInCode, btnZoomOutXml,
            btnZoomOutCode, btnRotateXml,
            btnRotateCode, btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode,
            btnBounceXml,
            btnBounceCode, btnCombineXml, btnCombineCode;

    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;
    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }
    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    private Animation initFadeInAnimation(){
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initFadeOutAnimation(){
        AlphaAnimation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initBlinkAnimation(){
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(300);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(3);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initZoomInAnimation(){
        ScaleAnimation animation = new ScaleAnimation(1,3,1,3, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initZoomOutAnimation(){
        ScaleAnimation animation = new ScaleAnimation(1,0.5f, 1,0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initRotateAnimation(){
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initCombineAnimation(){
        AnimationSet as = new AnimationSet(false);
        as.setFillEnabled(true);
        as.setInterpolator(new BounceInterpolator());

        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(2000);
        animation.setAnimationListener(animationListener);
        as.addAnimation(animation);
        as.addAnimation(animation);
        as.addAnimation(animation);

        ScaleAnimation animation2 = new ScaleAnimation(1,3, 1,3, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation2.setFillAfter(true);
        animation2.setDuration(4000);
        animation2.setAnimationListener(animationListener);
        as.addAnimation(animation2);

        return as;
    }

    private void handleClickAnimationXml(Button btn, int animId){
        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, animId);

        animation.setAnimationListener(animationListener);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ivUitLogo.startAnimation(animation);
            }
        });
    }

    private void handleClickAnimationCode(Button btn, final Animation animation) {
        // Handle onclickListenner to start animation
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        initVariables();

        Button btn_bai3;
        btn_bai3 = findViewById(R.id.btn_bai3);

        btn_bai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(MainActivity.this, Lab3_2.class);
                startActivity(iNewActivity);
            }
        });
        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(MainActivity.this, Main_Ex3.class);
                startActivity(iNewActivity);
                overridePendingTransition(R.anim.anim_fade_in, R.anim.right_to_left);
            }
        });

        //HandleClickAnimationXML
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        //HandleClickAnimationXML
        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());
    }

    private Animation initBounceAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1,1,0,1);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initSlideUpAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1,1,1,0);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initMoveAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0,1000,0,0);
        animation.setFillAfter(true);
        animation.setDuration(800);
        animation.setAnimationListener(animationListener);
        return animation;
    }
}