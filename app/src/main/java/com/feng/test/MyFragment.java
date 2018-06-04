package com.feng.test;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import com.feng.mvp.BaseFragment;
import com.feng.util.io.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://ups.youku.com/ups/get.json?ckey=7B19C0AB12633B22E7FE81271162026020570708D6CC189E4924503C49D243A0DE6CD84A766832C2C99898FC5ED31F3709BB3CDD82C96492E721BDD381735026&client_ip=30.96.89.34&client_ts=1520826136&utid=Vxmc8UTuyi4DALo61wu6B7Af&vid=XMzQ1MTA4NDU4MA==&ccode=01010101&showid=&show_videoseq=&playlist_id=&playlist_videoseq=&h265=1&point=1&language=&audiolang=1&media_type=standard,audio&password=&client_id=&tq=0&mac=&network=1000&brand=HUAWEI&os_ver=&app_ver=6.8.1.123&key_index=23570660&d_type=&needbf=1&needad=0&");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    String result = StreamUtils.inputStream2String(inputStream);
                    Log.d("getups", result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


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
