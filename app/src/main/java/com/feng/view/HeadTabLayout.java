package com.feng.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.feng.mvp.BaseView;
import com.feng.presenter.MyViewPresenter;

/**
 * Created by feng on 2017/8/16.
 */

public class HeadTabLayout extends RelativeLayout {


    public HeadTabLayout(Context context) {
        super(context);
        initMvp();
    }

    public HeadTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMvp();
    }

    public HeadTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMvp();
    }

    private void initMvp() {

    }
}
