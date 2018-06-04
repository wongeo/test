package com.feng.fragment;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.feng.mvp.BaseFragment;

/**
 * Created by feng on 2017/12/13.
 */

public class GestureFragment extends BaseFragment implements View.OnTouchListener {
    private View mView;
    private double lastFingerDis;

    private boolean mIsOnTouch = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = new View(getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mView.setLayoutParams(params);
            mView.setBackgroundColor(Color.RED);
            mView.setOnTouchListener(this);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGestureDetector = new GestureDetector(getContext(), new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int pointerCount = event.getPointerCount();
        if (pointerCount >= 3) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mIsOnTouch = true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mIsOnTouch = false;
        }

        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && 2 == pointerCount) {
            lastFingerDis = distanceBetweenFingers(event);
            mIsOnTouch = true;
            return true;
        } else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP && 2 == pointerCount) {
            if (distanceBetweenFingers(event) > lastFingerDis) {
                Toast.makeText(getContext(), "放大", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "缩小", Toast.LENGTH_LONG).show();
            }
            mIsOnTouch = false;
            return true;
        } else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE && 2 == pointerCount) {
            return true;
        }

        if (!mIsOnTouch) {
            return false;
        }
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * 计算两个手指之间的距离。
     *
     * @param event
     * @return 两个手指之间的距离
     */
    private double distanceBetweenFingers(MotionEvent event) {
        float disX = Math.abs(event.getX(0) - event.getX(1));
        float disY = Math.abs(event.getY(0) - event.getY(1));
        return Math.sqrt(disX * disX + disY * disY);
    }

    private GestureDetector mGestureDetector;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        int mode;

        @Override
        public boolean onDown(MotionEvent e) {
            mode = -1;
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (mode == -1) {
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    mode = 3;
                } else {
                    if (e1.getX() <= mView.getWidth() / 2) {
                        mode = 0;
                    } else {
                        mode = 1;
                    }
                }
            }
            Log.d("GestureFragment", "onScroll distanceX:" + distanceX + "  distanceY:" + distanceY);
            Log.d("GestureFragment", "onScroll mode:" + mode);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}
