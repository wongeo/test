package com.feng.test;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.feng.mvp.BaseFragment;
import com.feng.util.BASE64;
import com.zhangyue.aac.player.AacError;
import com.zhangyue.aac.player.AacPlayer;

/**
 * Created by feng on 2017/9/14.
 */

public class TestFragment extends BaseFragment implements View.OnClickListener {
    Button mButton;

    AacPlayer mMediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        mButton = (Button) view.findViewById(R.id.btn_play);
        mButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        mMediaPlayer = new AacPlayer();
        mMediaPlayer.setOnErrorListener(mOnErrorListener);
        mMediaPlayer.stop();
        mMediaPlayer.setDataSource("/storage/emulated/0/iReader/album/files/60/50000716.abk", BASE64.decode("R+/FyQOsyGgnF9yru1k3RAzqIovL2DfDQL08gdJMPw4Pz4pM7VpjqiH2tsl/1q4Qfa30bcxsk8jIVRVnHSXle4njXs8H+r8gORvOp9z4vsfG+61CCrTzRwmtdstrlitNSMLWAJxLlDSM1Y3j1vWKt9GJZv5MhtxPXyvF+Lr5fNI="));
        mMediaPlayer.seekTo(0);
        mMediaPlayer.start();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(Util.getNotificationIconId())
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(false)
                .setOngoing(true)
                .setContentIntent(mContentIntent)
                .setDeleteIntent(mDeleteIntent)
                .addAction(status == TTSStatus.Play ? R.drawable.ic_pause_black_24dp : R.drawable.ic_play_black_24dp,
                        status == TTSStatus.Play ? "Pause" : "Play",
                        PendingIntent.getBroadcast(mContext, 0, new Intent(CONSTANT.NOTIFICATION_ACTION_TTS_PLAY),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(R.drawable.ic_close_black_24dp, "Exit", PendingIntent.getBroadcast(mContext, 0,
                        new Intent(CONSTANT.NOTIFICATION_ACTION_TTS_EXIT), PendingIntent.FLAG_UPDATE_CURRENT))
                .setStyle(new NotificationCompat.MediaStyle()
                        //.setMediaSession(mNotificationTTS.getSessionToken())
                        .setShowActionsInCompactView(0, 1))
                .setColor(mContext.getResources().getColor(R.color.notification_primary_color));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && mLargeIcon != null) {
            // 对于 21 以下的版本，还不支持 Material Design 式的通知栏，我们为其设置大图标。
            builder.setLargeIcon(mLargeIcon);
        }

    }

    private AacPlayer.OnErrorListener mOnErrorListener = new AacPlayer.OnErrorListener() {
        @Override
        public void onError(AacPlayer mp, AacError error) {
            Exception ex = null;

        }
    };
}
