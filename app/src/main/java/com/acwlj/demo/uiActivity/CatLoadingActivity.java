package com.acwlj.demo.uiActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.acwlj.demo.R;
import com.nooeyes.github.CatLoadingView;

import com.nooeyes.github.arontibo.ElasticDownloadView;
import com.nooeyes.github.arontibo.ProgressDownloadView;

/**
 * Created by lj on 2016/4/19.
 */
public class CatLoadingActivity extends AppCompatActivity implements  View.OnClickListener{
    CatLoadingView mView;

    ElasticDownloadView mElasticDownloadView;
    Button but_sucess, but_faile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_catloading);

        mView = new CatLoadingView();
        findViewById(R.id.button_cal).setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mView.show(getSupportFragmentManager(), "");
                    }
                });

        mElasticDownloadView = (ElasticDownloadView) findViewById(R.id.elastic_download_view);
        findViewById(R.id.but_sucess).setOnClickListener(this);
        findViewById(R.id.but_faile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_sucess:
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        mElasticDownloadView.startIntro();
                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mElasticDownloadView.success();
                    }
                }, 2 * ProgressDownloadView.ANIMATION_DURATION_BASE);
                break;
            case R.id.but_faile:
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        mElasticDownloadView.startIntro();
                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mElasticDownloadView.setProgress(45);
                    }
                }, 2 * ProgressDownloadView.ANIMATION_DURATION_BASE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mElasticDownloadView.fail();
                    }
                }, 3 * ProgressDownloadView.ANIMATION_DURATION_BASE);
                break;

            default:
                break;
        }
    }
}
