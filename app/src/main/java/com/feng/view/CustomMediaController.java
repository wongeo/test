package com.feng.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.feng.test.R;
import com.feng.util.TimeUtils;

/**
 * Created by wj148202 on 2017/11/9.
 */

public class CustomMediaController extends FrameLayout implements IMediaController, View.OnClickListener {

    private ImageView mPlay;
    private TextView mPosition;
    private TextView mDuration;
    private SeekBar mSeekBar;

    public CustomMediaController(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomMediaController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomMediaController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.venvy_media_controller, this);
        mPlay = (ImageView) findViewById(R.id.venvy_start);
        mPosition = (TextView) findViewById(R.id.venvy_position_text);
        mDuration = (TextView) findViewById(R.id.venvy_total_time);
        mSeekBar = (SeekBar) findViewById(R.id.venvy_seekbar);
        mSeekBar.setMax(10000);
    }

    @Override
    public boolean isShowing() {
        return getVisibility() == View.VISIBLE;
    }

    @Override
    public void onShow() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void onHide() {
        setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        mPlay.setBackgroundResource(R.drawable.venvy_start_player_icon);
    }

    @Override
    public void onStart() {
        mPlay.setBackgroundResource(R.drawable.venvy_pause_player_icon);
    }

    @Override
    public void onPositionChange(long position, long duration) {
        mPosition.setText(TimeUtils.timeToMediaString2(position));
        mDuration.setText(TimeUtils.timeToMediaString2(duration));
        int progress = (int) (position * 1.0f / duration * mSeekBar.getMax());
        mSeekBar.setProgress(progress);
    }

    @Override
    public void onClick(View v) {

    }
}
