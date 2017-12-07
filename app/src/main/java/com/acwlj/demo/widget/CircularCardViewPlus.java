package com.acwlj.demo.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class CircularCardViewPlus extends CardView {

    private int mColor;

    public CircularCardViewPlus(Context context) {
        this(context, null);
    }

    public CircularCardViewPlus(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularCardViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setCardBackgroundColor(int color) {
        super.setCardBackgroundColor(color);
        mColor = color;
    }

//    public int getCardBackgroundColor() {
//        return mColor;
//    }
}
