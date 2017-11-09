package com.feng.view;

/**
 * Created by wj148202 on 2017/11/9.
 */

public interface IMediaController {

    boolean isShowing();

    void onShow();

    void onHide();

    void onStop();

    void onStart();

    void onPositionChange(long position, long duration);
}
