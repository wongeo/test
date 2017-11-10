package com.feng.video.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.feng.media.PlayerState;
import com.feng.mvp.BaseFragment;
import com.feng.video.R;
import com.feng.video.view.MediaBottomView;
import com.feng.video.view.CustomVideoView;

/**
 * Created by feng on 2017/11/9.
 */

public class CustomVideoFragment extends BaseFragment<CustomVideoPresenter> implements View.OnTouchListener, MediaBottomView.IMediaBottomViewListener {

    public CustomVideoView mVideoView;

    public MediaBottomView mMediaBottomView;

    private View mRootView;

    public CustomVideoFragment() {
        CustomVideoPresenter presenter = new CustomVideoPresenter(this);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.custom_video_fragment, container, false);

            mVideoView = (CustomVideoView) mRootView.findViewById(R.id.custom_video_view);
            mVideoView.setOnTouchListener(this);
            mMediaBottomView = (MediaBottomView) mRootView.findViewById(R.id.media_bottom_view);
            mMediaBottomView.setListener(this);
        }
        return mRootView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        toggleMediaControlsVisiblity();
        return false;
    }

    private void toggleMediaControlsVisiblity() {
        if (mMediaBottomView == null) {
            return;
        }
        if (mMediaBottomView.isShowing()) {
            mMediaBottomView.hide();
        } else {
            mMediaBottomView.show();
        }
    }

    @Override
    public void onStartClick() {
        if (mVideoView.getPlayerState() == PlayerState.PAUSED || mVideoView.getPlayerState() == PlayerState.STOPED) {
            mVideoView.play();
        } else {
            mVideoView.pause();
        }
    }

    @Override
    public void onSeekTo(float percent) {

    }
}
