package com.acwlj.demo.ui.original;

import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

//import butterknife.InjectView;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.view.FlowLayout;

import butterknife.Bind;

/**
 * author DONG 2015/5/8.
 */
public class FlowLayoutActivity extends BaseActivity {
    @Bind(R.id.flow_layout)
    FlowLayout mFlowLayout;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_flowlayout;
    }

    @Override
    protected void initPageView() {
        initActionBar();
    }

    @Override
    protected void initPageViewListener() {

    }

    @Override
    protected void process(Bundle savedInstanceState) {

    }

    public void onHello(View view) {
        startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.activity_packagemanager, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.show_system:

                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_flowlayout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
