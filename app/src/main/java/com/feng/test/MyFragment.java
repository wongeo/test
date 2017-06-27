package com.feng.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

    private View mButton;

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

        new AlertDialog.Builder(getActivity())
                .setTitle("确认")
                .setMessage("确定吗？")
                .setPositiveButton("是", null)
                .setNegativeButton("否", null)
                .show();
    }
}
