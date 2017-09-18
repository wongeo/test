package com.feng.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.feng.mvp.BaseFragment;

/**
 * Created by feng on 2017/9/14.
 */

public class TestFragment extends BaseFragment implements View.OnClickListener {
    Button mButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        mButton = (Button) view.findViewById(R.id.btn_play);
        mButton.setOnClickListener(this);
        return view;
    }

    private boolean mIsPlaying;

    @Override
    public void onClick(View v) {

        Context context = getActivity();


        PendingIntent playIntent = PendingIntent.getBroadcast(context, 0, new Intent(context.getPackageName() + ".play"), PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("title")
                .setContentText("context")
                .setAutoCancel(false)
                .setOngoing(true)
//                .setContentIntent(mContentIntent)
//                .setDeleteIntent(mDeleteIntent)
                .addAction(mIsPlaying ? android.R.drawable.ic_media_pause : android.R.drawable.ic_media_play, mIsPlaying ? "Pause" : "Play", playIntent)
                .setStyle(new NotificationCompat.MediaStyle().setShowCancelButton(true)
                        .setShowActionsInCompactView(0));

        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notifyManager.notify(1, builder.build());
    }
}
