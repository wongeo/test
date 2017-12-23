package com.feng.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.feng.mvp.BaseFragment;
import com.feng.ref.RelayWeakReference;
import com.feng.test.R;

import java.lang.ref.WeakReference;

/**
 * Created by feng on 2017/12/18.
 */

public class WeakReferenceFragment extends BaseFragment implements View.OnClickListener {

    private WeakReference<View> mInflatedViewRef;
    private Button mButton5;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.weakreference_fragment, container, false);
        mButton5 = (Button) view.findViewById(R.id.button5);
        mButton5.setOnClickListener(this);

        View view1 = new View(getContext());
        view1.setBackgroundColor(Color.RED);
        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(300, 300);


        View view2 = new View(getContext());
        view2.setBackgroundColor(Color.BLUE);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(200, 200);


        view.addView(view1, params1);
        view.addView(view2, params2);

        final View view3 = new View(getContext());
        View view4 = new View(getContext());
        mRefreence2 = new WeakReference<View>(view4);
        mRefreence = new RelayWeakReference<View>(view3, new RelayWeakReference.Callable<View>() {
            @Override
            public View call() {
                return view3;
            }
        });
        Log.d("WeakReferenceFragment", "view3:" + view3.hashCode());
        Log.d("WeakReferenceFragment", "view4:" + view3.hashCode());

        return view;
    }

    RelayWeakReference<View> mRefreence;

    WeakReference<View> mRefreence2;

    byte[] buffer;


    int i1 = 10, i2 = 2, i3 = 3, i4 = 4;

    @Override
    public void onClick(View v) {
        int gravity1 = i1 | i2 | i3;
        int gravity2 = i1 | i2;
        int gravity3 = i1;

//        buffer = new byte[1 * 100 * 1024 * 1024];
        System.gc();
        View view = mRefreence.getObj();


    }
}
