package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.mvp.BaseFragment;
import com.feng.test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by feng on 2017/9/17.
 */

public class RuntimeDemoFragment extends BaseFragment implements View.OnClickListener {

    private View mBtnAdbVersion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.runtime_demo_fragment, container, false);
        mBtnAdbVersion = view.findViewById(R.id.btn_adb_version);
        mBtnAdbVersion.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        Runtime mRuntime = Runtime.getRuntime();
        try {
            //Process中封装了返回的结果和执行错误的结果
            Process mProcess = mRuntime.exec("ls");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));

            StringBuffer respBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                respBuffer.append(line);
            }
            bufferedReader.close();
            System.out.println(respBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}