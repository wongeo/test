package com.feng.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.feng.mvp.BaseView;
import com.feng.presenter.MyViewPresenter;

/**
 * Created by wj148202 on 2017/10/30.
 */

public class MyCustomView extends RelativeLayout implements BaseView {

    private MyViewPresenter mPresenter;

    public MyCustomView(Context context) {
        super(context);
        init(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPresenter = new MyViewPresenter(this);
    }
}
