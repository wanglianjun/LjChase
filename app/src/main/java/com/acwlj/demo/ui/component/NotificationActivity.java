package com.acwlj.demo.ui.component;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Button;

//import butterknife.InjectView;
import butterknife.Bind;
import butterknife.OnClick;
import com.acwlj.demo.MyApp;
import com.acwlj.demo.R;
import com.acwlj.demo.ui.common.BaseActivity;
import com.acwlj.demo.ui.main.MainActivity;
import com.acwlj.demo.util.NotificationHelper;

/**
 * 通知栏
 *
 * @author dong on 15/11/10.
 */
public class NotificationActivity extends BaseActivity {
    @Bind(R.id.button_create)
    Button createButton;

    private int mCount;
    private NotificationManagerCompat mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_notification;
    }

    @Override
    protected void init() {
        super.init();
        mNotificationManager = NotificationManagerCompat.from(MyApp.getInstance());
    }

    @OnClick(R.id.button_create)
    public void create() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentText("content")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        mNotificationManager.notify("1", R.id.notification, mBuilder.build());
        NotificationHelper.INSTANCE.vibrateAndSound();
    }

    @OnClick(R.id.button_modify)
    public void modify() {
        mBuilder.setNumber(mCount++);
        mNotificationManager.notify("2", R.id.notification, mBuilder.build());
    }

    @OnClick(R.id.button_remove)
    public void remove() {
        mNotificationManager.cancel("1", R.id.notification);
    }
}
