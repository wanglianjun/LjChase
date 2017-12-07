package com.acwlj.demo.uiActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.acwlj.demo.R;
import com.acwlj.demo.widget.CircularCardViewPlus;

import com.nooeyes.github.circular.animation.SupportAnimator;
import com.nooeyes.github.circular.animation.ViewAnimationUtils;

public class CircularSample3Activity extends AppCompatActivity
        implements OnPreDrawListener{

    private CircularCardViewPlus mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_sample_3);

        mContentView = (CircularCardViewPlus) findViewById(R.id.content);

        getViewTreeObserver().addOnPreDrawListener(this);
    }

    protected View getRootView(){
        return mContentView;
    }

    protected ViewTreeObserver getViewTreeObserver(){
        return getRootView().getViewTreeObserver();
    }

    protected void startRevealTransition(){
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(getRootView(),
                getRootView().getRight(), getRootView().getBottom(), 0,
                CircularSample2Activity.hypo(getRootView().getHeight(), getRootView().getWidth()),
                View.LAYER_TYPE_SOFTWARE);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @Override
    public boolean onPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        startRevealTransition();
        return true;
    }
}
