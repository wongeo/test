package com.feng.test;


import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.feng.fastxml.XML;
import com.feng.mvp.BasePresenter;
import com.feng.util.io.FileUtils;

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
