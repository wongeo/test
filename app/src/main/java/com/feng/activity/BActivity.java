package com.feng.activity;

import android.os.Bundle;

import com.feng.mvp.BaseActivity;
import com.feng.test.MyFragment;

public class BActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getClass().getSimpleName());
        startFragment(new MyFragment());
    }
}
