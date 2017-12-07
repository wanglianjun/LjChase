package com.nooeyes.github.circular.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.nooeyes.github.circular.animation.RevealAnimator;
import com.nooeyes.github.circular.animation.SupportAnimator;
import com.nooeyes.github.circular.animation.ViewAnimationUtils;

/**
 * CircularReveal
 * //compile 'com.github.ozodrukh:CircularReveal:16.4.14'
 ==============

 Lollipop ViewAnimationUtils.createCircularReveal for everyone 2.3+

 <img src="http://7sbnrp.com1.z0.glb.clouddn.com/lollipop2-CircularReveal.gif" />

 <a href="http://www.youtube.com/watch?feature=player_embedded&v=_vVpwzYb4Dg
 " target="_blank">Yotube Video <br /> <img src="http://img.youtube.com/vi/_vVpwzYb4Dg/0.jpg"
 alt="Ripple DEMO" width="320" height="240" border="10" /></a>

 Sample
 ======
 <a href="https://github.com/ozodrukh/CircularReveal/releases"> Sample & .aar file </a>

 Note
 ====

 depends from Jake Wharton's NineOldsAndroid, or use my modifed version (included auto cancel)

 Using
 ======

 Use regular `RevealFrameLayout` & `RevealLinearLayout` don't worry, only target will be clipped :)

 ```xml
 <io.codetail.widget.RevealFrameLayout
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:layout_width="match_parent"
 android:layout_height="match_parent">

 <!-- Put more views here if you want, it's stock frame layout  -->

 <android.support.v7.widget.CardView
 xmlns:app="http://schemas.android.com/apk/res-auto"
 android:id="@+id/awesome_card"
 style="@style/CardView"
 app:cardBackgroundColor="@color/material_deep_teal_500"
 app:cardElevation="2dp"
 app:cardPreventCornerOverlap="false"
 app:cardUseCompatPadding="true"
 android:layout_marginLeft="8dp"
 android:layout_marginRight="8dp"
 android:layout_marginTop="8dp"
 android:layout_width="300dp"
 android:layout_height="300dp"
 android:layout_gravity="center_horizontal"
 />

 </io.codetail.widget.RevealFrameLayout>
 ```

 ```java

 View myView = findView(R.id.awesome_card);

 // get the center for the clipping circle
 int cx = (myView.getLeft() + myView.getRight()) / 2;
 int cy = (myView.getTop() + myView.getBottom()) / 2;

 // get the final radius for the clipping circle
 int dx = Math.max(cx, myView.getWidth() - cx);
 int dy = Math.max(cy, myView.getHeight() - cy);
 float finalRadius = (float) Math.hypot(dx, dy);

 SupportAnimator animator =
 ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
 animator.setInterpolator(new AccelerateDecelerateInterpolator());
 animator.setDuration(1500);
 animator.start();

 ```

 ####API that need to mention

 #####Cancel it!

 ```java

 SupportAnimator animator = ... ;
 animator.cancel();

 ```

 #####Reverse it!

 ```java

 SupportAnimator animator = ... ;
 animator = animator.reverse(); // override with new one

 ```

 How to add dependency
 =====================

 This library is not released in Maven Central, but instead you can use [JitPack](https://www.jitpack.io/)

 add remote maven url

 ```groovy
 repositories {
 maven {
 url "https://jitpack.io"
 }
 }
 ```

 then add a library dependency

 ```groovy
 dependencies {
 compile ('com.github.ozodrukh:CircularReveal:1.3.1@aar') {
 transitive = true;
 }
 }
 ```


 License
 --------

 The MIT License (MIT)

 Copyright (c) 2014 Abdullaev Ozodrukh

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.

 */
public class RevealFrameLayout extends FrameLayout implements RevealAnimator{

    private Path mRevealPath;
    private final Rect mTargetBounds = new Rect();
    private RevealInfo mRevealInfo;
    private boolean mRunning;
    private float mRadius;

    public RevealFrameLayout(Context context) {
        this(context, null);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mRevealPath = new Path();
    }

    @Override
    public void onRevealAnimationStart() {
        mRunning = true;
    }

    @Override
    public void onRevealAnimationEnd() {
        mRunning = false;
        invalidate(mTargetBounds);
    }

    @Override
    public void onRevealAnimationCancel() {
        onRevealAnimationEnd();
    }

    /**
     * Circle radius size
     *
     * @hide
     */
    @Override
    public void setRevealRadius(float radius){
        mRadius = radius;
        mRevealInfo.getTarget().getHitRect(mTargetBounds);
        invalidate(mTargetBounds);
    }

    /**
     * Circle radius size
     *
     * @hide
     */
    @Override
    public float getRevealRadius(){
        return mRadius;
    }

    /**
     * @hide
     */
    @Override
    public void attachRevealInfo(RevealInfo info) {
        mRevealInfo = info;
    }

    /**
     * @hide
     */
    @Override
    public SupportAnimator startReverseAnimation() {
        if(mRevealInfo != null && mRevealInfo.hasTarget() && !mRunning) {
            return ViewAnimationUtils.createCircularReveal(mRevealInfo.getTarget(),
                    mRevealInfo.centerX, mRevealInfo.centerY,
                    mRevealInfo.endRadius, mRevealInfo.startRadius);
        }
        return null;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if(mRunning && child == mRevealInfo.getTarget()){
            final int state = canvas.save();

            mRevealPath.reset();
            mRevealPath.addCircle(mRevealInfo.centerX, mRevealInfo.centerY, mRadius, Path.Direction.CW);

            canvas.clipPath(mRevealPath);

            boolean isInvalided = super.drawChild(canvas, child, drawingTime);

            canvas.restoreToCount(state);

            return isInvalided;
        }

        return super.drawChild(canvas, child, drawingTime);
    }

}
