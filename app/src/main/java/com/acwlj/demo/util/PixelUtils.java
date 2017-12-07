package com.acwlj.demo.util;

import android.content.res.Resources;

/**
 * 像素转换工具类.
 */
public class PixelUtils {
    private static Resources resources = Resources.getSystem();
    private static int densityDpi = resources.getDisplayMetrics().densityDpi;
    private static float scaledDensity = resources.getDisplayMetrics().scaledDensity;

    public static int dp2px(float value) {
        final float scale = densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    public static int px2dp(float value) {
        final float scale = densityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }

    public static int sp2px(float value) {
        float spvalue = value * scaledDensity;
        return (int) (spvalue + 0.5f);
    }

    public static int px2sp(float value) {
        final float scale = scaledDensity;
        return (int) (value / scale + 0.5f);
    }

}
