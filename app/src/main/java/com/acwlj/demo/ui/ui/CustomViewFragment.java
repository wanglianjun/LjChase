package com.acwlj.demo.ui.ui;

import android.os.Bundle;

import butterknife.Bind;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseFragment;
import com.acwlj.demo.view.CustomView;

/**
 * @author dong on 15/4/20.
 */
public class CustomViewFragment extends BaseFragment {
    @Bind(R.id.custom)
    CustomView customView;

    @Override
    protected int initPageLayoutId() {
        return R.layout.fragment_customview;
    }

    @Override
    public void onPageFirstVisible() {
        customView.setState(4);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
