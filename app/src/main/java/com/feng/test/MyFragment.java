package com.feng.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.feng.mvp.BaseFragment;


/**
 * Created by feng on 2017/4/6.
 */
public class MyFragment extends BaseFragment<MyPresenter> implements View.OnClickListener {

    private View mRootView;


    public MyFragment() {
        MyPresenter presenter = new MyPresenter(this);
        this.setPresenter(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.my_fragment, container, false);
        }
        return mRootView;
    }

    @Override
    public void onClick(View v) {
    }

}
