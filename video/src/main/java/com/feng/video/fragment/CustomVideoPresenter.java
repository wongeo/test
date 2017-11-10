package com.feng.video.fragment;

import android.os.Bundle;
import android.view.View;

import com.feng.media.IPlayStateCallback;
import com.feng.media.PlayerState;
import com.feng.mvp.BasePresenter;

/**
 * Created by wj148202 on 2017/11/10.
 */

public class CustomVideoPresenter extends BasePresenter<CustomVideoFragment> {

    public CustomVideoPresenter(CustomVideoFragment view) {
        super(view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getView().mVideoView.setPlayStateCallback(mPlayStateCallback);
        getView().mVideoView.play();
    }

    private IPlayStateCallback mPlayStateCallback = new IPlayStateCallback() {
        @Override
        public void onMediaError(Exception ex) {
            getView().mMediaBottomView.onStop();
        }

        @Override
        public void onBufferingProgressChanged(int percent) {

        }

        @Override
        public void onPlayerStateChanged(@PlayerState.Status int newState) {
            if (newState == PlayerState.PLAYING) {
                getView().mMediaBottomView.onStart();
            } else {
                getView().mMediaBottomView.onStop();
            }
        }

        @Override
        public void onPrepared(int duration) {

        }

        @Override
        public void onPlayPositionChanged(float percent, final long position, final long duration) {
            if (!isViewAttached()) {
                return;
            }
            getView().getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!isViewAttached()) {
                        return;
                    }
                    getView().mMediaBottomView.onPositionChange(position, duration);
                }
            });
        }

        @Override
        public void onCompletion() {
            getView().mMediaBottomView.onStop();
        }
    };
}
