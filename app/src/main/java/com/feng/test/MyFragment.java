package com.feng.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.activity.BActivity;
import com.feng.activity.PermissionCompatActivity;
import com.feng.mvp.BaseFragment;
import com.feng.util.io.FileUtils;


/**
 * Created by feng on 2017/4/6.
 */
public class MyFragment extends BaseFragment<MyPresenter> implements View.OnClickListener {

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

        }
        return mRootView;
    }


    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            PermissionCompatActivity activity = (PermissionCompatActivity) getActivity();
            activity.requestAllPermissions();
            try {
                byte[] raw = FileUtils.read(Environment.getExternalStorageDirectory() + "/configmanager.json");
                String str = new String(raw);
                System.out.println();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (v == mButton2) {

            startActivity(new Intent(getActivity(), BActivity.class));
        }
    }
}
