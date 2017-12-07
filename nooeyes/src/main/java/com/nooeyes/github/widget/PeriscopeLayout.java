package com.nooeyes.github.widget;

/**
 * Created by lj on 2017/7/21.
 */


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;
import com.nooeyes.alex.R;
/**
 * # PeriscopeLayout
 A layout with animation like Periscope's

 一个类似Periscope点赞效果的Layout,效果如下:

 ![效果图](img/periscope.gif)

 对应的实现文章在这里:[一步一步教你实现Periscope点赞效果
 ](http://www.jianshu.com/p/03fdcfd3ae9c)

 ## Add dependency

 ### Gradle
 `compile 'me.yifeiyuan.periscopelayout:library:1.0.0'`

 ### Maven
 ```
 <dependency>
 <groupId>me.yifeiyuan</groupId>
 <artifactId>periscopelayout</artifactId>
 <version>library</version>
 <type>xml</type>
 </dependency>
 ```

 ### Eclipse
 呵呵

 ## Usage

 **Step One:**

 ```
 <me.yifeiyuan.library.PeriscopeLayout
 android:id="@+id/periscope"
 android:layout_width="300dp"
 android:layout_height="300dp"
 android:text="Hello World!"
 android:background="#d2d2c9"
 />
 ```

 **Step Two:**

 ```
 final PeriscopeLayout periscopeLayout = (PeriscopeLayout) findViewById(R.id.periscope);
 periscopeLayout.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
periscopeLayout.addHeart();
}
});
 compile 'me.yifeiyuan.periscopelayout:library:1.0.0'
 */
public class PeriscopeLayout extends RelativeLayout {

    private Interpolator line = new LinearInterpolator();//线性
    private Interpolator acc = new AccelerateInterpolator();//加速
    private Interpolator dce = new DecelerateInterpolator();//减速
    private Interpolator accdec = new AccelerateDecelerateInterpolator();//先加速后减速
    private Interpolator[] interpolators;

    private int mHeight;
    private int mWidth;
    private LayoutParams lp;
    private Drawable[] drawables;
    private Random random = new Random();

    private int dHeight;
    private int dWidth;

    public PeriscopeLayout(Context context) {
        super(context);
        init();
    }

    public PeriscopeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        //初始化显示的图片
        drawables = new Drawable[3];
        Drawable red = getResources().getDrawable(R.drawable.like_pl_red);
        Drawable yellow = getResources().getDrawable(R.drawable.like_pl_yellow);
        Drawable blue = getResources().getDrawable(R.drawable.like_pl_blue);

        drawables[0] = red;
        drawables[1] = yellow;
        drawables[2] = blue;
        //获取图的宽高 用于后面的计算
        //注意 我这里3张图片的大小都是一样的,所以我只取了一个
        dHeight = red.getIntrinsicHeight();
        dWidth = red.getIntrinsicWidth();

        //底部 并且 水平居中
        lp = new LayoutParams(dWidth, dHeight);
        lp.addRule(CENTER_HORIZONTAL, TRUE);//这里的TRUE 要注意 不是true
        lp.addRule(ALIGN_PARENT_BOTTOM, TRUE);

        // 初始化插补器
        interpolators = new Interpolator[4];
        interpolators[0] = line;
        interpolators[1] = acc;
        interpolators[2] = dce;
        interpolators[3] = accdec;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }


    public void addHeart() {

        ImageView imageView = new ImageView(getContext());
        //随机选一个
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        imageView.setLayoutParams(lp);

        addView(imageView);

        Animator set = getAnimator(imageView);
        set.addListener(new AnimEndListener(imageView));
        set.start();

    }

    private Animator getAnimator(View target) {
        AnimatorSet set = getEnterAnimtor(target);

        ValueAnimator bezierValueAnimator = getBezierValueAnimator(target);

        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playSequentially(set);
        finalSet.playSequentially(set, bezierValueAnimator);
        finalSet.setInterpolator(interpolators[random.nextInt(4)]);
        finalSet.setTarget(target);
        return finalSet;
    }

    private AnimatorSet getEnterAnimtor(final View target) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);
        enter.setTarget(target);
        return enter;
    }

    private ValueAnimator getBezierValueAnimator(View target) {

        //初始化一个贝塞尔计算器- - 传入
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2), getPointF(1));

        //这里最好画个图 理解一下 传入了起点 和 终点
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF((mWidth - dWidth) / 2, mHeight - dHeight), new PointF(random.nextInt(getWidth()), 0));
        animator.addUpdateListener(new BezierListenr(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 获取中间的两个 点
     *
     * @param scale
     */
    private PointF getPointF(int scale) {

        PointF pointF = new PointF();
        pointF.x = random.nextInt((mWidth - 100));//减去100 是为了控制 x轴活动范围,看效果 随意~~
        //再Y轴上 为了确保第二个点 在第一个点之上,我把Y分成了上下两半 这样动画效果好一些  也可以用其他方法
        pointF.y = random.nextInt((mHeight - 100)) / scale;
        return pointF;
    }

    private class BezierListenr implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public BezierListenr(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //这里获取到贝塞尔曲线计算出来的的x y值 赋值给view 这样就能让爱心随着曲线走啦
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);
            // 这里顺便做一个alpha动画
            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }


    private class AnimEndListener extends AnimatorListenerAdapter {
        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            //因为不停的add 导致子view数量只增不减,所以在view动画结束后remove掉
            removeView((target));
        }
    }

    //def siteUrl = 'https://github.com/AlanCheen/PeriscopeLayout'      // 项目的主页
//def gitUrl = 'git@github.com:AlanCheen/PeriscopeLayout.git'   // Git仓库的url
//group = "me.yifeiyuan.periscopelayout"                                        // Maven Group ID for the artifact，一般填你唯一的包名
//install {
//    repositories.mavenInstaller {
//        // This generates POM.xml with proper parameters
//        pom {
//            project {
//                packaging 'aar'
//                // Add your description here
//                name 'A layout with animation like Periscopes' 	//项目描述
//                url siteUrl
//                // Set your license
//                licenses {
//                    license {
//                        name 'The Apache Software License, Version 2.0'
//                        url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
//                    }
//                }
//                developers {
//                    developer {
//                        id 'alancheen'		//填写的一些基本信息
//                        name '程序亦非猿'
//                        email 'alancheen06@gmail.com'
//                    }
//                }
//                scm {
//                    connection gitUrl
//                    developerConnection gitUrl
//                    url siteUrl
//                }
//            }
//        }
//    }
//}
//task sourcesJar(type: Jar) {
//    from android.sourceSets.main.java.srcDirs
//    classifier = 'sources'
//}
//task javadoc(type: Javadoc) {
//    source = android.sourceSets.main.java.srcDirs
//    classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
//}
//task javadocJar(type: Jar, dependsOn: javadoc) {
//    classifier = 'javadoc'
//    from javadoc.destinationDir
//}
//artifacts {
//    archives javadocJar
//    archives sourcesJar
//}
//Properties properties = new Properties()
//properties.load(project.rootProject.file('local.properties').newDataInputStream())
//bintray {
//    user = properties.getProperty("bintray.user")
//    key = properties.getProperty("bintray.apikey")
//    configurations = ['archives']
//    pkg {
//        repo = "maven"
//        name = "PeriscopeLayout"	//发布到JCenter上的项目名字
//        websiteUrl = siteUrl
//        vcsUrl = gitUrl
//        licenses = ["Apache-2.0"]
//        publish = true
//    }
//}
}
