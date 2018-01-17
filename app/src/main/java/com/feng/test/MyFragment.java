package com.feng.test;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mvp.BaseFragment;


/**
 * Created by feng on 2017/4/6.
 */
public class MyFragment extends BaseFragment<MyPresenter> implements View.OnClickListener, View.OnTouchListener {

    private View mRootView;

    private View mButton1;
    private View mButton2;

    public MyFragment() {
        MyPresenter presenter = new MyPresenter(this);
        this.setPresenter(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.my_fragment, container, false);
            mButton1 = mRootView.findViewById(R.id.button1);
            mButton2 = mRootView.findViewById(R.id.button2);
            mButton1.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton2.setOnTouchListener(this);

            mRootView.findViewById(R.id.view1).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            Log.d("", "");
                            return true;
                        case MotionEvent.ACTION_UP:
                            Log.d("", "");
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            Log.d("", "");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.d("", "");
                            return true;
                        default:
                            break;
                    }
                    return true;
                }
            });
            mRootView.findViewById(R.id.view2).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            Log.d("", "");
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.d("", "");
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            Log.d("", "");
                            break;
                        case MotionEvent.ACTION_SCROLL:
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });

        }
        return mRootView;
    }


    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            ObjectAnimator.ofFloat(mButton2, "translationY", -300).setDuration(300).start();
        } else if (v == mButton2) {
            ObjectAnimator.ofFloat(mButton2, "translationY", 0).setDuration(300).start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("", "");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("", "");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("", "");
                break;
            case MotionEvent.ACTION_SCROLL:
                break;
            default:
                break;
        }
        return false;
    }
}
