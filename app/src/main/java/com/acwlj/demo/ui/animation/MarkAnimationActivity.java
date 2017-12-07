package com.acwlj.demo.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

//import butterknife.InjectView;
import butterknife.Bind;
import butterknife.OnClick;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.util.L;
import com.acwlj.demo.view.MarkView;

/**
 * @author dong on 15/3/9.
 */
public class MarkAnimationActivity extends BaseActivity {

    @Bind(R.id.mark)
    MarkView markView;
    @Bind(R.id.scale_text)
    TextView scaleTextView;
    @Bind(R.id.scale_seekbar)
    SeekBar scaleSeekBar;
    @Bind(R.id.btn_start)
    Button startButton;
    @Bind(R.id.btn_flash)
    Button flashButton;

    private float animScale;

    private AnimatorSet flashAnim;
    private boolean isActive = false;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_mark_up_demo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scaleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean mUser) {
                animScale = (float) ((float) progress / seekBar.getMax() + 1.0);
                scaleTextView.setText(String.valueOf(animScale));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.btn_start)
    void likeMark() {
        markView.like(animScale);

        isActive = true;
        if (flashAnim != null) {
            flashAnim.cancel();
        }
        isActive = false;
    }

    @OnClick(R.id.btn_flash)
    void flashAnim() {
        ValueAnimator inAnim = ObjectAnimator.ofFloat(markView, "alpha", 0.2f, 0.7f).setDuration(1500);
        ValueAnimator outAnim = ObjectAnimator.ofFloat(markView, "alpha", 0.7f, 0.2f).setDuration(1500);
        ValueAnimator showAnim = ValueAnimator.ofFloat(0f, 1f).setDuration(3000);

        flashAnim = new AnimatorSet();
        flashAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                L.d(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                L.d(TAG, "onAnimationEnd");
                if (!isActive) {
                    flashAnim.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                L.d(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                L.d(TAG, "onAnimationRepeat");
            }
        });
        flashAnim.playSequentially(inAnim, showAnim, outAnim);
        flashAnim.start();
    }
}
