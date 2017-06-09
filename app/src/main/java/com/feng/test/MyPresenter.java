package com.feng.test;


import android.os.Bundle;
import android.view.View;

import com.feng.mvp.BasePresenter;

/**
 * Created by feng on 2017/4/6.
 */
public class MyPresenter extends BasePresenter<MyFragment> {
    public MyPresenter(MyFragment view) {
        super(view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public boolean onBackPress() {
        return super.onBackPress();
    }

    private void bindData() {

    }

    public void onClick() {

    }
}
