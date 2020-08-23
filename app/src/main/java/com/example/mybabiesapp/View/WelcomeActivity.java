package com.example.mybabiesapp.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mybabiesapp.R;

public class WelcomeActivity extends Activity {
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        image = findViewById(R.id.anim1);
        text = findViewById(R.id.txtanim);
        AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(image,"translationY",-1000.0f,0.0f),
                            ObjectAnimator.ofFloat(image,"alpha",0.0f,1.0f),
                            ObjectAnimator.ofFloat(text,"translationX",-200.0f,0.0f)
                    );
                    animatorSet.setDuration(2000);
                    animatorSet.addListener(new AnimatorListenerAdapter(){
                        @Override public void onAnimationEnd(Animator animation) {

                            AnimatorSet animatorSet2 = new AnimatorSet();
                            animatorSet2.playTogether(
                                    ObjectAnimator.ofFloat(image,"scaleX", 1f, 0.5f, 1f),
                                    ObjectAnimator.ofFloat(image,"scaleY", 1f, 0.5f, 1f)
                            );
                            animatorSet2.setInterpolator(new AccelerateInterpolator());
                            animatorSet2.setDuration(1000);
                            animatorSet2.start();
                        }
                    });
                    animatorSet.start();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {

                    Intent intent = new Intent("com.example.mybabiesapp.View.MAINACTIVITY");
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
