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

    private Button mBotton;

    private ClubDownloadStateView mView;

    private float progress = 0.0f;

    private Abc ttt;

    public MyFragment() {
        MyPresenter presenter = new MyPresenter(this);
        this.setPresenter(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.my_fragment, container, false);
//            mBotton = (Button) mRootView.findViewById(R.id.button);

            mView = (ClubDownloadStateView) mRootView.findViewById(R.id.buttonPanel);
            mView.setOnClickListener(this);
            ttt = new Abc();
        }
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        if (v == mBotton) {
            mPresenter.onClick();

            ttt.start();
        } else if (v == mView) {
            ttt.start();
        }
    }

    private void ddd() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                mRootView.post(new Runnable() {
                    @Override
                    public void run() {

                        progress += 0.1;
                        if (progress > 1) {
                            progress = 0;
                        }
                        mView.setProgress(progress);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    class Abc extends Thread {
        private volatile int iii = 0;

        @Override
        public void run() {
            super.run();
            iii++;
            System.out.println(iii + "");

        }
    }
}
