package com.acwlj.demo.uiActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.uifragment.AcDemoFragment;
import com.acwlj.demo.uifragment.DianZanFragment;

/**
 * Created by lj on 2016/4/13.
 */
public class AcDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DianZanFragment fmAcd=new DianZanFragment();
        AcDemoFragment fmAcd=new AcDemoFragment();
        fmAcd.setAcDemoToolbar(new AcDemoSetToolbar() {
            @Override
            public void onSetToolbar(int color) {
                getActionBarToolbar().setBackgroundColor(color);
            }
        });
        FragmentManager fmManager=getSupportFragmentManager();
        FragmentTransaction fmTrans=fmManager.beginTransaction();
        fmTrans.replace(R.id.fragment_frame,fmAcd);
        fmTrans.commit();
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_acdemo;
    }

    public interface AcDemoSetToolbar{
        void onSetToolbar(int color);
    }
}
