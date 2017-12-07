package com.acwlj.demo;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import timber.log.Timber;
/**
 * Created by lj on 2016/4/12.
 * #  个人Demo合集

 ## 更新日志
 2014.10.24 调整工程结构到Android Studio
 2015.5.24 合并之前因为环境不同而分裂的两个Demo工程，并整理分类，首页通过导航抽屉切换分类

 ![Home](http://upload-images.jianshu.io/upload_images/111373-8d56767100cd785c.png)
 Tip:导航菜单中的图标全部来自Google的[Material icons](https://www.google.com/design/icons/)项目，提供了非常多的图片供我们选择，好顶赞

 */
public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initStrictMode();
        initLogger();
        RealmHelper.init();
    }

    /**
     * 初始化StrictMode
     */
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                            // .penaltyDeath()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                            // .penaltyDeath()
                    .build());
        }
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new LogReportingTree());
        }
    }

    private static class LogReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return;
            }
            // 将部分日志报告服务器
        }
    }
}
