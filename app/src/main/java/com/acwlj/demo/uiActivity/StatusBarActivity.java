package com.acwlj.demo.uiActivity;

import com.acwlj.demo.util.JaegerStatusbarUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acwlj.demo.R;

/**
 * Created by Jaeger on 16/2/14.
 *
 * Email: chjie.jaeger@gamil.com GitHub: https://github.com/laobie
 * 
 * compile 'com.android.support:appcompat-v7:23.1.1' compile
 * 'com.android.support:design:23.1.1'
 */
public class StatusBarActivity extends StatusBarBaseActivity {
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private CheckBox mChbTranslucent;
	private Button mBtnSetColor;
	private Button mBtnSetTransparent;
	private Button mBtnSetTranslucent;
	private ViewGroup contentLayout;
	private SeekBar mSbChangeAlpha;
	private TextView mTvStatusAlpha;

	private int mStatusBarColor;
	private int mAlpha = JaegerStatusbarUtil.DEFAULT_STATUS_BAR_ALPHA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_statusbar);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		contentLayout = (ViewGroup) findViewById(R.id.main);
		mToolbar = (Toolbar) findViewById(R.id.toolbarMain);
		mChbTranslucent = (CheckBox) findViewById(R.id.chb_translucent);
		mBtnSetColor = (Button) findViewById(R.id.btn_set_color);
		mBtnSetTransparent = (Button) findViewById(R.id.btn_set_transparent);
		mBtnSetTranslucent = (Button) findViewById(R.id.btn_set_translucent);
		mSbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);
		mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);

		try {
			setSupportActionBar(mToolbar);
		} catch (Exception e) {
			Toast.makeText(this,
					"63设置标题栏" + Build.VERSION.SDK_INT + "/" + e.toString(),
					Toast.LENGTH_SHORT).show();
		}
		try {
			ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
					mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
					R.string.navigation_drawer_close);
			mDrawerLayout.setDrawerListener(toggle);
			toggle.syncState();

			mBtnSetColor.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(StatusBarActivity.this,
							StatusBarColorActivity.class);
					startActivity(intent);
				}
			});

			mBtnSetTransparent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(StatusBarActivity.this,
							StatusBarImageActivity.class);
					intent.putExtra(
							StatusBarImageActivity.EXTRA_IS_TRANSPARENT, true);
					startActivity(intent);
				}
			});

			mBtnSetTranslucent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(StatusBarActivity.this,
							StatusBarImageActivity.class);
					intent.putExtra(
							StatusBarImageActivity.EXTRA_IS_TRANSPARENT, false);
					startActivity(intent);
				}
			});

			mChbTranslucent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mChbTranslucent.isChecked()) {
						contentLayout.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.statusbar_bg_monkey));
						JaegerStatusbarUtil.setTranslucentForDrawerLayout(
								StatusBarActivity.this, mDrawerLayout, mAlpha);
						mToolbar.setBackgroundColor(getResources().getColor(
								android.R.color.transparent));
					} else {
						contentLayout.setBackgroundDrawable(null);
						mToolbar.setBackgroundColor(getResources().getColor(
								R.color.colorPrimary));
						JaegerStatusbarUtil.setColorForDrawerLayout(
								StatusBarActivity.this, mDrawerLayout,
								getResources().getColor(R.color.colorPrimary),
								mAlpha);
					}
				}
			});

			mSbChangeAlpha.setMax(255);
			mSbChangeAlpha
					.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
						@Override
						public void onProgressChanged(SeekBar seekBar,
								int progress, boolean fromUser) {
							mAlpha = progress;
							if (mChbTranslucent.isChecked()) {
								JaegerStatusbarUtil.setTranslucentForDrawerLayout(
										StatusBarActivity.this, mDrawerLayout,
										mAlpha);
							} else {
								JaegerStatusbarUtil.setColorForDrawerLayout(
										StatusBarActivity.this, mDrawerLayout,
										mStatusBarColor, mAlpha);
							}
							mTvStatusAlpha.setText(String.valueOf(mAlpha));
						}

						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {

						}

						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {

						}
					});
			mSbChangeAlpha.setProgress(JaegerStatusbarUtil.DEFAULT_STATUS_BAR_ALPHA);
		} catch (Exception e) {
			Toast.makeText(this,
					"/半透明矩形view" + Build.VERSION.SDK_INT + "/" + e.toString(),
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void setStatusBar() {
		mStatusBarColor = getResources().getColor(R.color.colorPrimary);
		JaegerStatusbarUtil.setColorForDrawerLayout(this,
				(DrawerLayout) findViewById(R.id.drawer_layout),
				mStatusBarColor, mAlpha);
	}
}
