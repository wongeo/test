package com.feng.video.view;

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

import com.feng.util.TimeUtils;
import com.feng.video.R;

/**
 * Created by wj148202 on 2017/11/9.
 */

public class MediaBottomView extends FrameLayout implements View.OnClickListener {

    private ImageView mStart;
    private TextView mPosition;
    private TextView mDuration;
    private SeekBar mSeekBar;

    private IMediaBottomViewListener mListener;

    public MediaBottomView(@NonNull Context context) {
        super(context);
        init();
    }

    public MediaBottomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MediaBottomView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.venvy_media_controller, this);
        mStart = (ImageView) findViewById(R.id.venvy_start);
        mPosition = (TextView) findViewById(R.id.venvy_position_text);
        mDuration = (TextView) findViewById(R.id.venvy_total_time);
        mSeekBar = (SeekBar) findViewById(R.id.venvy_seekbar);

        mStart.setOnClickListener(this);

    }

    public void setListener(IMediaBottomViewListener listener) {
        mListener = listener;
    }

    /**
     * 是否显示
     *
     * @return
     */
    public boolean isShowing() {
        return getVisibility() == View.VISIBLE;
    }


    public void hide() {
        setVisibility(View.GONE);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void onStop() {
        mStart.setBackgroundResource(R.drawable.venvy_start_player_icon);
    }

    public void onStart() {
        mStart.setBackgroundResource(R.drawable.venvy_pause_player_icon);
    }

    public void onPositionChange(long position, long duration) {
        mPosition.setText(TimeUtils.timeToMediaString2(position));
        mDuration.setText(TimeUtils.timeToMediaString2(duration));
        int progress = (int) (position * 1.0f / duration * mSeekBar.getMax());
        mSeekBar.setProgress(progress);
    }

    @Override
    public void onClick(View v) {
        if (v == mStart) {
            if (mListener != null) {
                mListener.onStartClick();
            }
        }
    }

    public interface IMediaBottomViewListener {
        void onStartClick();

        void onSeekTo(float position);
    }
}
