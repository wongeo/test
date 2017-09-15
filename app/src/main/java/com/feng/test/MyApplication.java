package com.feng.test;

import android.app.Application;

import com.zhangyue.iReader.app.APP;

/**
 * Created by feng on 2017/9/15.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        APP.setmAppContext(this);
    }
}
