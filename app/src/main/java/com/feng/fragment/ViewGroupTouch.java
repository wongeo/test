package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mvp.BaseFragment;
import com.feng.test.R;

/**
 * Created by feng on 2017/12/26.
 */

public class ViewGroupTouch extends BaseFragment {
    View view1, view2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewgroup_touch_fragment, container, false);
        view1 = view.findViewById(R.id.view1);
        view2 = view.findViewById(R.id.view2);

        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("view1 ACTION_DOWN");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    System.out.println("view1 ACTION_UP");
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    System.out.println("view1 ACTION_MOVE");
                }
                return false;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("view2 ACTION_DOWN");
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    System.out.println("view2 ACTION_UP");
                }
                return false;
            }
        });
        return view;
    }
}
