package com.nooeyes.github.widget;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

/**
 * Created by Miroslaw Stanek on 21.12.2015.
 */
public class LikeBtnCircleView extends View {
    private static final int START_COLOR = 0xFFFF5722;
    private static final int END_COLOR = 0xFFFFC107;

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private Paint circlePaint = new Paint();
    private Paint maskPaint = new Paint();

    private Bitmap tempBitmap;
    private Canvas tempCanvas;

    private float outerCircleRadiusProgress = 0f;
    private float innerCircleRadiusProgress = 0f;

    private int maxCircleSize;

    public LikeBtnCircleView(Context context) {
        super(context);
        init();
    }

    public LikeBtnCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeBtnCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @SuppressLint("NewApi")//21
    public LikeBtnCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super(context, attrs, defStyleAttr, defStyleRes);
//        }
        init();
    }

    private void init() {
        circlePaint.setStyle(Paint.Style.FILL);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxCircleSize = w / 2;
        tempBitmap = Bitmap.createBitmap(getWidth(), getWidth(), Bitmap.Config.ARGB_8888);
        tempCanvas = new Canvas(tempBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tempCanvas.drawColor(0xffffff, PorterDuff.Mode.CLEAR);
        tempCanvas.drawCircle(getWidth() / 2, getHeight() / 2, outerCircleRadiusProgress * maxCircleSize, circlePaint);
        tempCanvas.drawCircle(getWidth() / 2, getHeight() / 2, innerCircleRadiusProgress * maxCircleSize, maskPaint);
        canvas.drawBitmap(tempBitmap, 0, 0, null);
    }

    public void setInnerCircleRadiusProgress(float innerCircleRadiusProgress) {
        this.innerCircleRadiusProgress = innerCircleRadiusProgress;
        postInvalidate();
    }

    public float getInnerCircleRadiusProgress() {
        return innerCircleRadiusProgress;
    }

    public void setOuterCircleRadiusProgress(float outerCircleRadiusProgress) {
        this.outerCircleRadiusProgress = outerCircleRadiusProgress;
        updateCircleColor();
        postInvalidate();
    }

    private void updateCircleColor() {
        float colorProgress = (float) LikeBtnUtils.clamp(outerCircleRadiusProgress, 0.5, 1);
        colorProgress = (float) LikeBtnUtils.mapValueFromRangeToRange(colorProgress, 0.5f, 1f, 0f, 1f);
        this.circlePaint.setColor((Integer) argbEvaluator.evaluate(colorProgress, START_COLOR, END_COLOR));
    }

    public float getOuterCircleRadiusProgress() {
        return outerCircleRadiusProgress;
    }

    public static final Property<LikeBtnCircleView, Float> INNER_CIRCLE_RADIUS_PROGRESS =
            new Property<LikeBtnCircleView, Float>(Float.class, "innerCircleRadiusProgress") {
                @Override
                public Float get(LikeBtnCircleView object) {
                    return object.getInnerCircleRadiusProgress();
                }

                @Override
                public void set(LikeBtnCircleView object, Float value) {
                    object.setInnerCircleRadiusProgress(value);
                }
            };

    public static final Property<LikeBtnCircleView, Float> OUTER_CIRCLE_RADIUS_PROGRESS =
            new Property<LikeBtnCircleView, Float>(Float.class, "outerCircleRadiusProgress") {
                @Override
                public Float get(LikeBtnCircleView object) {
                    return object.getOuterCircleRadiusProgress();
                }

                @Override
                public void set(LikeBtnCircleView object, Float value) {
                    object.setOuterCircleRadiusProgress(value);
                }
            };
}
