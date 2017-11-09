package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.feng.mvp.BaseFragment;
import com.feng.view.CustomMediaController;
import com.feng.view.CustomVideoView;

/**
 * Created by wj148202 on 2017/11/9.
 */

public class CustomVideoFragment extends BaseFragment {

    private CustomVideoView mVideoView;

    private CustomMediaController mMediaController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mVideoView == null) {
            mVideoView = new CustomVideoView(getActivity());
            mMediaController = new CustomMediaController(getActivity());
            mVideoView.setMediaController(mMediaController);
            mVideoView.play();
        }
        return mVideoView;
    }
}
