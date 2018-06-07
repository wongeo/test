package com.feng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mvp.BaseFragment;
import com.feng.test.R;

public class HandlerThreadFragment extends BaseFragment implements View.OnClickListener {

    HandlerThread mHandlerThread;
    Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.handler_fragment, container, false);
        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mHandlerThread = new HandlerThread("PlayerThread");
        Log.d("HandlerThread", "isInterrupted:" + mHandlerThread.isInterrupted());
        mHandlerThread.start();
        Log.d("HandlerThread", "isInterrupted:" + mHandlerThread.isInterrupted());
        mHandler = new Handler(mHandlerThread.getLooper());
        Log.d("HandlerThread", "isInterrupted:" + mHandlerThread.isInterrupted());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                mHandlerThread.quit();
                Log.d("HandlerThread", "isInterrupted:" + mHandlerThread.isInterrupted());
                break;
            case R.id.button2:
                mHandlerThread.start();
                Log.d("HandlerThread", "isInterrupted:" + mHandlerThread.isInterrupted());
                break;
            case R.id.button3:
                mHandler.removeMessages(5555);
                break;
            default:
                break;
        }
    }

}
