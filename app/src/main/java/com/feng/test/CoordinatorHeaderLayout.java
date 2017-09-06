package com.feng.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by feng on 2017/7/5.
 */

public class CoordinatorHeaderLayout extends LinearLayout {

    private View mTabLayout;

    private View mHeadLayout;

    private int mMaxOffset;

    public CoordinatorHeaderLayout(Context context) {
        super(context);
    }

    public CoordinatorHeaderLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mMaxOffset = mHeadLayout.getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
