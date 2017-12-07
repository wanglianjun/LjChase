package com.acwlj.demo.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import java.util.HashMap;

//import butterknife.InjectView;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.animation.AnimationActivity;
import com.acwlj.demo.ui.animation.MarkAnimationActivity;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.ui.component.FragmentManageActivity;
import com.acwlj.demo.ui.component.IntentsActivity;
import com.acwlj.demo.ui.component.NotificationActivity;
import com.acwlj.demo.ui.component.SendDataOneActivity;
import com.acwlj.demo.ui.component.SendDataTwoActivity;
import com.acwlj.demo.ui.component.ServiceActivity;
import com.acwlj.demo.ui.library.ImageLoaderActivity;
import com.acwlj.demo.ui.library.OttoActivity;
import com.acwlj.demo.ui.library.RealmActivity;
import com.acwlj.demo.ui.library.RxJavaActivity;
import com.acwlj.demo.ui.library.XListViewActivity;
import com.acwlj.demo.ui.original.FadeoutHeaderRecyclerViewActivity;
import com.acwlj.demo.ui.original.FlexibleHeaderRecyclerViewActivity;
import com.acwlj.demo.ui.original.FlowLayoutActivity;
import com.acwlj.demo.ui.original.audio.AudioRecorderActivity;
import com.acwlj.demo.ui.original.calendar.CalendarActivity;
import com.acwlj.demo.ui.other.ImageSelectorActivity;
import com.acwlj.demo.ui.other.Md5Activity;
import com.acwlj.demo.ui.other.PackageManageActivity;
import com.acwlj.demo.ui.other.ScreenshotActivity;
import com.acwlj.demo.ui.other.WebViewActivity;
import com.acwlj.demo.ui.sensor.GeocoderActivity;
import com.acwlj.demo.ui.storage.BitmapSaveLocalActivity;
import com.acwlj.demo.ui.storage.ContentProviderActivity;
import com.acwlj.demo.ui.text.AutoCompleteActivity;
import com.acwlj.demo.ui.text.EditTextActivity;
import com.acwlj.demo.ui.text.TextAdvanceActivity;
import com.acwlj.demo.ui.text.TextSizeActivity;
import com.acwlj.demo.ui.text.TextViewSpanActivity;
import com.acwlj.demo.ui.ui.DialogActivity;
import com.acwlj.demo.ui.ui.DrawableStateActivity;
import com.acwlj.demo.ui.ui.FragmentTabHostActivity;
import com.acwlj.demo.ui.ui.PopupWindowActivity;
import com.acwlj.demo.ui.ui.RecyclerViewActivity;
import com.acwlj.demo.ui.ui.SwipeRefreshLayoutActivity;
import com.acwlj.demo.ui.ui.ViewPagerActivity;
import com.acwlj.demo.ui.ui.touch.TouchEventActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    private static HashMap<Integer, Class[]> sNavigationMap;

    static {
        Class[] originalActivities = {
                CalendarActivity.class,
                FlexibleHeaderRecyclerViewActivity.class,
                FadeoutHeaderRecyclerViewActivity.class,
                FlowLayoutActivity.class,
                AudioRecorderActivity.class
        };
        Class[] libraryActivities = {
                ImageLoaderActivity.class,
                XListViewActivity.class,
                OttoActivity.class,
                RxJavaActivity.class,
                RealmActivity.class
        };
        Class[] componentActivities = {
                ServiceActivity.class,
                FragmentManageActivity.class,
                NotificationActivity.class,
                SendDataOneActivity.class,
                SendDataTwoActivity.class,
                IntentsActivity.class
        };
        Class[] uiActivities = {
                TouchEventActivity.class,
                RecyclerViewActivity.class,
                ViewPagerActivity.class,
                SwipeRefreshLayoutActivity.class,
                FragmentTabHostActivity.class,
                DialogActivity.class,
                PopupWindowActivity.class,
                DrawableStateActivity.class
        };
        Class[] textActivities = {
                TextSizeActivity.class,
                EditTextActivity.class,
                AutoCompleteActivity.class,
                TextViewSpanActivity.class,
                TextAdvanceActivity.class
        };
        Class[] animationActivities = {
                AnimationActivity.class,
                MarkAnimationActivity.class
        };
        Class[] sensorActivities = {
                GeocoderActivity.class
        };
        Class[] storageActivities = {
                ContentProviderActivity.class,
                BitmapSaveLocalActivity.class
        };
        Class[] otherActivities = {
                WebViewActivity.class,
                PackageManageActivity.class,
                ScreenshotActivity.class,
                ImageSelectorActivity.class,
                Md5Activity.class
        };
        sNavigationMap = new HashMap<>();
        sNavigationMap.put(R.id.navigation_original, originalActivities);
        sNavigationMap.put(R.id.navigation_library, libraryActivities);
        sNavigationMap.put(R.id.navigation_component, componentActivities);
        sNavigationMap.put(R.id.navigation_ui, uiActivities);
        sNavigationMap.put(R.id.navigation_text, textActivities);
        sNavigationMap.put(R.id.navigation_animation, animationActivities);
        sNavigationMap.put(R.id.navigation_sensor, sensorActivities);
        sNavigationMap.put(R.id.navigation_storage, storageActivities);
        sNavigationMap.put(R.id.navigation_other, otherActivities);
    }

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;
    private int currentNavigationId;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPageView() {
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getActionBarToolbar(), R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    protected void initPageViewListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (sNavigationMap.containsKey(menuItem.getItemId())) {
                    menuItem.setChecked(true);
                    setTitle(menuItem.getTitle());
                    currentNavigationId = menuItem.getItemId();
                    MainContentFragment contentFragment = (MainContentFragment) getSupportFragmentManager().findFragmentById(R.id.content_layout);
                    if (contentFragment != null) {
                        contentFragment.updateContentList(sNavigationMap.get(currentNavigationId));
                    }
                    mDrawerLayout.closeDrawers();
                    return true;
                } else // todo
                    return menuItem.getItemId() == R.id.navigation_settings;
            }
        });
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        currentNavigationId = R.id.navigation_original;
        MainContentFragment fragment = new MainContentFragment();
        fragment.updateContentList(sNavigationMap.get(currentNavigationId));
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment).commit();
    }

}
