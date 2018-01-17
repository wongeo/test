package com.feng.test;


import android.os.Bundle;
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

        MyCallback call1 = new MyCallback();
        call1.weight = 1;
        call1.name = "name1";
        MyCallback call2 = new MyCallback();
        call2.weight = 2;
        call2.name = "name2";
        MyCallback call3 = new MyCallback();
        call3.weight = 3;
        call3.name = "name3";
        MyCallback call4 = new MyCallback();
        call4.weight = 2;
        call4.name = "name4";

        mCallbacks.add(call1);
        mCallbacks.add(call2);
        mCallbacks.add(call3);
        mCallbacks.add(call4);


        Collections.sort(mCallbacks, new Comparator<ICallback>() {
            @Override
            public int compare(ICallback o1, ICallback o2) {
                return o2.weight() - o1.weight();
            }
        });

        for (ICallback callback : mCallbacks) {
            callback.exc();
        }

    }

    public class MyCallback implements ICallback {

        public int weight;
        public String name;

        @Override
        public int weight() {
            return weight;
        }

        @Override
        public void exc() {

        }
    }
}
