package com.example.alek.task.mvvm.view.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.alek.task.mvvm.model.Callback;

/**
 * Created by alek on 14/09/2017.
 */

public  abstract class LifecycleAppCompatActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    public void inTransition(){
            // Try and catch protects if the animated view is detached during animation
            // eq. User continuously switching between display modes while changing activities
            View  reveal_bg = getRevealBg();
            FloatingActionButton fab = getFab();

            if (reveal_bg.getVisibility() == View.VISIBLE) {
                // wait for the view to be created
                reveal_bg.post(() -> {
                    int cx = reveal_bg.getWidth();
                    int cy = reveal_bg.getHeight();

                    int x = (int) fab.getX() + fab.getWidth() / 2;
                    int y = (int) fab.getY() + fab.getHeight() / 2;

                    float finalRadius = (float) (Math.hypot(cx, cy));
                    try {
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(reveal_bg, x, y, finalRadius, 0);

                        fab.getDrawable().setAlpha(0);

                        anim.setDuration(350);
                        anim.setInterpolator(new AccelerateDecelerateInterpolator());
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                reveal_bg.setVisibility(View.INVISIBLE);

                                ObjectAnimator animator = ObjectAnimator
                                        .ofPropertyValuesHolder(fab.getDrawable(),
                                                PropertyValuesHolder.ofInt("alpha", 0, 255));
                                animator.setTarget(fab.getDrawable());
                                animator.setDuration(100);
                                animator.start();
                            }
                        });
                        anim.start();
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    }
                });
            }
    }

    public void outTransition(Callback callback) {

            View reveal_bg = getRevealBg();
            FloatingActionButton fab = getFab();

            ObjectAnimator animator = ObjectAnimator
                    .ofPropertyValuesHolder(fab.getDrawable(),
                            PropertyValuesHolder.ofInt("alpha", 255, 0));
            animator.setTarget(fab.getDrawable());
            animator.setDuration(100);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    // get the center for the clipping circle
                    int cx = reveal_bg.getWidth();
                    int cy = reveal_bg.getHeight();


                    int x = (int) fab.getX() + fab.getWidth() / 2;
                    int y = (int) fab.getY() + fab.getHeight() / 2;

                    float finalRadius = (float) (Math.hypot(cx, cy));

                    reveal_bg.setVisibility(View.VISIBLE);

                    try {

                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(reveal_bg, x, y, 0, finalRadius);
                        anim.setDuration(350);
                        anim.setInterpolator(new AccelerateDecelerateInterpolator());
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                transtionOutCallback(callback);
                            }
                        });

                        anim.start();
                    }catch(IllegalStateException e){
                        e.printStackTrace();
                    }
                }
            });
            animator.start();
    }

    protected abstract void transtionOutCallback(Callback callback);

    protected abstract FloatingActionButton getFab();

    protected abstract View getRevealBg();
}
