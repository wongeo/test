package com.feng.test;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;

/**
 * Created by feng on 2017/7/5.
 */

public class MyScrollView extends ScrollView {
    private WebView mWebView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("MyScrollView", "mWebView.getTop()" + mWebView.getTop());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("MyScrollView", "onInterceptTouchEvent" + getScrollY());

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        Log.d("MyScrollView", "onTouchEvent" + getScrollY());
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mWebView = (WebView) getChildAt(0);
        mWebView.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("MyScrollView", "mWebView.getTop()");
            }
        });
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
}
