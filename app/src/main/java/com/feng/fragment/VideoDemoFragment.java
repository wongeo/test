package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mvp.BaseFragment;
import com.feng.test.R;
import com.feng.video.fragment.CustomVideoFragment;

/**
 * Created by wj148202 on 2017/11/9.
 */

public class VideoDemoFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_demo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentManager().beginTransaction().add(R.id.container_custom_video, new CustomVideoFragment()).commit();
    }
}
