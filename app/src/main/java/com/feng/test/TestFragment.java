package com.feng.test;

import android.os.Bundle;
import android.util.Log;

import com.feng.mvp.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by feng on 2017/9/14.
 */

public class TestFragment extends BaseFragment {

    private List<ICallback> mCallbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallbacks = new ArrayList<>();

        //创建观察者，并将其一一加入观察者集合
        mCallbacks.add(new MyCallback("aaa", 4));
        mCallbacks.add(new MyCallback("bbb", 1));
        mCallbacks.add(new MyCallback("ccc", 2, true));
        mCallbacks.add(new MyCallback("ddd", 3));

        //对集合进行排序
        Collections.sort(mCallbacks, new Comparator<ICallback>() {
            @Override
            public int compare(ICallback o1, ICallback o2) {
                //等级大的先执行
                return o2.level() - o1.level();
            }
        });

        //遍历运行
        for (ICallback callback : mCallbacks) {
            callback.run();
            if (callback.intercept()) {
                break;
            }
        }
    }

    private class MyCallback implements ICallback {
        private final int mLevel;
        private final boolean mIntercept;
        private String mName;


        public MyCallback(String name, int level) {
            this(name, level, false);
        }


        public MyCallback(String name, int level, boolean intercept) {
            mName = name;
            mLevel = level;
            mIntercept = intercept;
        }

        @Override
        public int level() {
            return mLevel;
        }

        @Override
        public int run() {
            return Log.d("Callback", mName + ":" + mLevel);
        }

        @Override
        public boolean intercept() {
            return mIntercept;
        }
    }

    interface ICallback {

        /**
         * 等级
         *
         * @return
         */
        int level();

        /**
         * 运行
         *
         * @return
         */
        int run();

        /**
         * 是否拦截
         *
         * @return
         */
        boolean intercept();
    }
}
