package com.nooeyes.github.arontibo.VectorCompat;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface Tintable {
    public void setTintMode(PorterDuff.Mode tintMode);

    public void setTintList(ColorStateList tint);
}
