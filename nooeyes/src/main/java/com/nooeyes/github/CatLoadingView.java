package com.nooeyes.github;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.nooeyes.alex.R;
import com.nooeyes.github.catloading.EyelidView;
import com.nooeyes.github.catloading.GraduallyTextView;


/**
 * 小猫加载动画
 * # Android CatLoadingView

 This project idea is from [Link](http://mp.weixin.qq.com/s?__biz=MjM5MDMxOTE5NA==&mid=402703079&idx=2&sn=2fcc6746a866dcc003c68ead9b68e595&scene=2&srcid=0302A7p723KK8E5gSzLKb2ZL&from=timeline&isappinstalled=0#wechat_redirect) .<br>
 Thanks for the idea.<br>

 I like the animation in that picture.<br>

 ![](http://ww4.sinaimg.cn/mw690/a695acdejw1f2hbmfxzk5g208u0a2ass.gif)

 ..as you see right now,wish you like it.

 ### Step

 Import this project into android studio..it's build with it.

 ###  Usage

 #### Gradle

 ```
 compile project(':library')
 ```

 ####  config in java code

 CatLoadingView mView;


 @Override protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 mView = new CatLoadingView();
 findViewById(R.id.button).setOnClickListener(
 new View.OnClickListener() {
 @Override public void onClick(View v) {
 mView.show(getSupportFragmentManager(), "");
 }
 });
 }

 ##

 This view is adjust in Nexue5 but not test in the other size screen .

 ## About me

 A small guy  in mainland FUJIAN China.

 If you have any new idea about this project, feel free to tell me ,Tks. :smiley:


 ## License

 The MIT License (MIT)

 Copyright © 2015 Roger

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

 [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CatLoadingView-green.svg?style=true)](https://android-arsenal.com/details/1/3369)

 */
public class CatLoadingView extends DialogFragment {

    public CatLoadingView() {
    }


    Animation operatingAnim, eye_left_Anim, eye_right_Anim;

    Dialog mDialog;

    View mouse, eye_left, eye_right;

    EyelidView eyelid_left, eyelid_right;

    GraduallyTextView mGraduallyTextView;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            mDialog.setContentView(R.layout.catloading_main);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.getWindow().setGravity(Gravity.CENTER);

            operatingAnim = new RotateAnimation(360f, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            operatingAnim.setRepeatCount(Animation.INFINITE);
            operatingAnim.setDuration(2000);

            eye_left_Anim = new RotateAnimation(360f, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            eye_left_Anim.setRepeatCount(Animation.INFINITE);
            eye_left_Anim.setDuration(2000);

            eye_right_Anim = new RotateAnimation(360f, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            eye_right_Anim.setRepeatCount(Animation.INFINITE);
            eye_right_Anim.setDuration(2000);

            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            eye_left_Anim.setInterpolator(lin);
            eye_right_Anim.setInterpolator(lin);

            View view = mDialog.getWindow().getDecorView();

            mouse = view.findViewById(R.id.mouse);

            eye_left = view.findViewById(R.id.eye_left);

            eye_right = view.findViewById(R.id.eye_right);

            eyelid_left = (EyelidView) view.findViewById(R.id.eyelid_left);

            eyelid_left.setColor(Color.parseColor("#d0ced1"));

            eyelid_left.setFromFull(true);

            eyelid_right = (EyelidView) view.findViewById(R.id.eyelid_right);

            eyelid_right.setColor(Color.parseColor("#d0ced1"));

            eyelid_right.setFromFull(true);

            mGraduallyTextView = (GraduallyTextView) view.findViewById(
                    R.id.graduallyTextView);

            operatingAnim.setAnimationListener(
                    new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }


                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }


                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            eyelid_left.resetAnimator();
                            eyelid_right.resetAnimator();
                        }
                    });
        }
        return mDialog;
    }


    @Override
    public void onResume() {
        super.onResume();
        mouse.setAnimation(operatingAnim);
        eye_left.setAnimation(eye_left_Anim);
        eye_right.setAnimation(eye_right_Anim);
        eyelid_left.startLoading();
        eyelid_right.startLoading();
        mGraduallyTextView.startLoading();
    }


    @Override
    public void onPause() {
        super.onPause();

        operatingAnim.reset();
        eye_left_Anim.reset();
        eye_right_Anim.reset();

        mouse.clearAnimation();
        eye_left.clearAnimation();
        eye_right.clearAnimation();

        eyelid_left.stopLoading();
        eyelid_right.stopLoading();
        mGraduallyTextView.stopLoading();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mDialog = null;
        System.gc();
    }
}
