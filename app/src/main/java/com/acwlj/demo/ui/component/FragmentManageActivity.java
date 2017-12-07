package com.acwlj.demo.ui.component;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.util.FragmentUtil;

public class FragmentManageActivity extends BaseActivity {
    private FragmentManager fm;
    private int fragmentNum;

    @Override
    protected void init() {
        super.init();
        fm = getSupportFragmentManager();
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_fragment_test;
    }

    @Override
    protected void initPageViewListener() {
        fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d("main", "onBackStackChanged");
                Fragment currentFragment = fm.findFragmentById(R.id.container);
                if (currentFragment != null) {
                    String fragmentName = currentFragment.getClass().getName();
                    Log.d("main", fragmentName);
                }
            }
        });
    }

    public void OnClick(View v) {
        Bundle args = new Bundle();
        args.putInt("Num", fragmentNum);
        fragmentNum++;

        switch (v.getId()) {
            case R.id.button1:
                FragmentUtil.startFragment(fm, TestFragment.class, args);
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                FragmentUtil.replaceFragment(fm, TestFragment.class, args, true);
                break;
            case R.id.button4:
                FragmentUtil.replaceFragment(fm, TestFragment.class, args, false);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_customview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                fm.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentNum = 0;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
