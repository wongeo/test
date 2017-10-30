package com.feng.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.feng.mvp.BaseView;
import com.feng.presenter.MyViewPresenter;

/**
 * Created by wj148202 on 2017/10/30.
 */

public class MyCustomView extends RelativeLayout implements BaseView<MyViewPresenter> {

    private MyViewPresenter mPresenter;

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void inti() {
        mPresenter = new MyViewPresenter(this);
    }

    @Override
    public void setPresenter(MyViewPresenter presenter) {
        
    }
}
