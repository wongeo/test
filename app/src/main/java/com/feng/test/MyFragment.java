package com.feng.test;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.activity.BActivity;
import com.feng.activity.PermissionCompatActivity;
import com.feng.mvp.BaseFragment;
import com.feng.util.io.FileUtils;
import com.youku.runtimepermission.PermissionCompat;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;


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
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity() instanceof PermissionCompatActivity) {
                    PermissionCompatActivity activity = (PermissionCompatActivity) getActivity();
                    activity.requestAllPermissions();
                }
                System.out.println();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (v == mButton2) {

            startActivity(new Intent(getActivity(), BActivity.class));
        }
    }
}
