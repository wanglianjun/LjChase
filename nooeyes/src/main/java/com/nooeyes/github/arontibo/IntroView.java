package com.nooeyes.github.arontibo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;


import com.nooeyes.alex.R;
import com.nooeyes.github.arontibo.VectorCompat.AnimatedVectorDrawable;


/**
 * Created by thibaultguegan on 15/03/15.
 */
public class IntroView extends ImageView {

	private static final String LOG_TAG = IntroView.class.getSimpleName();

	public interface EnterAnimationListener {
		public void onEnterAnimationFinished();
	}

	private EnterAnimationListener mListener;

	/**
	 * MARK: Constructor
	 */

	@SuppressLint("NewApi")
	public IntroView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			setImageResource(R.drawable.arontibo_avd_start);
		} else {
			AnimatedVectorDrawable drawable = AnimatedVectorDrawable
					.getDrawable(context, R.drawable.arontibo_avd_start);
			setImageDrawable(drawable);
		}
	}

	/**
	 * MARK: Getters/setters
	 */

	public void setListener(EnterAnimationListener listener) {
		mListener = listener;
	}

	/**
	 * MARK: Public functions
	 */

	public void startAnimation() {

		Drawable drawable = getDrawable();
		Animatable animatable = (Animatable) drawable;

		AVDWrapper.Callback callback = new AVDWrapper.Callback() {
			@Override
			public void onAnimationDone() {
				Log.d(LOG_TAG, "Enter animation finished");
				mListener.onEnterAnimationFinished();
			}

			@Override
			public void onAnimationStopped() {

			}
		};

		AVDWrapper wrapper = new AVDWrapper(animatable, new Handler(), callback);
		wrapper.start(getContext().getResources().getInteger(
				R.integer.enter_animation_duration));
	}

}
