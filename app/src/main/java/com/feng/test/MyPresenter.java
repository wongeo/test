package com.feng.test;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.feng.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by feng on 2017/4/6.
 */
public class MyPresenter extends BasePresenter<MyFragment> {

    List<ICallback> mCallbacks;

    public MyPresenter(MyFragment view) {
        super(view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mCallbacks = new ArrayList<>();

        mCallbacks.add(new MyCallback("aaaa", 0));
        mCallbacks.add(new MyCallback("bbbb", 1));
        mCallbacks.add(new MyCallback("cccc", 2));
        mCallbacks.add(new MyCallback("dddd", 3));

        Collections.sort(mCallbacks, new Comparator<ICallback>() {
            @Override
            public int compare(ICallback o1, ICallback o2) {
                return o2.level() - o1.level();
            }
        });

        for (ICallback callback : mCallbacks) {
            callback.run();
        }
    }

    private class MyCallback implements ICallback {
        private final int mLevel;
        private String mName;

        public MyCallback(String name, int level) {
            mName = name;
            mLevel = level;
        }

        @Override
        public int level() {
            return mLevel;
        }

        @Override
        public int run() {
            return Log.d("CallbackManager", mName + ":" + mLevel);
        }

        @Override
        public boolean intercept() {
            return false;
        }
    }
}
