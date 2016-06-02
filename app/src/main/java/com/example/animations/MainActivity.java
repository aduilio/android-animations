package com.example.animations;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int ANIMATION_CIRCLE_DELAY = 2000;
    private final int ANIMATION_TRANSLATE_DELAY = 500;

    private ImageView circle;
    private TextView tvTranslate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CIRCLE ANIMATION
        circle = (ImageView) findViewById(R.id.iv_example_circle);
        startCircleAnimation();
        ////////////////////


        // STRIPPED ANIMATION
        AnimationDrawable strippedAnim = (AnimationDrawable) findViewById(R.id.iv_example_stripped).getBackground();
        strippedAnim.setOneShot(false);
        strippedAnim.start();


        // TRANSLATE ANIMATION
        tvTranslate = (TextView) findViewById(R.id.tv_example_translate);
        tvTranslate.setTag("CLOSED");
    }

    // CIRCLE ANIMATION

    private void startCircleAnimation() {
        ObjectAnimator termsConditionsAnim = ObjectAnimator.ofFloat(circle, "alpha", 1, 0);
        termsConditionsAnim.setDuration(ANIMATION_CIRCLE_DELAY);
        termsConditionsAnim.addListener(listener);
        termsConditionsAnim.start();

        int padding = (int) (80 * getResources().getDisplayMetrics().density);
        ValueAnimator animator = ValueAnimator.ofInt(padding, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int a = (Integer) valueAnimator.getAnimatedValue();
                circle.setPadding(a, a, a, a);
            }
        });
        animator.setDuration(ANIMATION_CIRCLE_DELAY);
        animator.start();
    }

    private Animator.AnimatorListener listener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            startCircleAnimation();
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    // END CIRCLE ANIMATION


    // TRANSLATE ANIMATION

    public void onTextViewClick(View view) {
        if (tvTranslate.getTag().equals("CLOSED")) {
            tvTranslate.setTag("OPENED");

            ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationY", 0, tvTranslate.getHeight());
            translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
            translateAnim.start();

            tvTranslate.setText(Html.fromHtml("Clique &#x25B2;"));
        } else {
            tvTranslate.setTag("CLOSED");

            ObjectAnimator translateAnim = ObjectAnimator.ofFloat(tvTranslate, "translationY", tvTranslate.getTranslationY(), 0);
            translateAnim.setDuration(ANIMATION_TRANSLATE_DELAY);
            translateAnim.start();

            tvTranslate.setText(Html.fromHtml("Clique &#x25BC;"));
        }
    }
}
