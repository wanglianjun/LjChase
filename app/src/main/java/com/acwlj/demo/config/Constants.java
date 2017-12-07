package com.acwlj.demo.config;

import android.os.Environment;

/**
 * 常量
 *
 * @author dong 2014-7-28
 */
public class Constants {
    public static class Path {
        public static final String BASE_DIR = Environment.getExternalStorageDirectory().getPath();
    }

    public static class Extra {
        public static final String IMAGES = "com.acwlj.demo.IMAGES";
        public static final String IMAGE_POSITION = "com.acwlj.demo.IMAGE_POSITION";
    }

}
