package com.feng.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import com.feng.mvp.BaseActivity;

/**
 * Created by wj148202 on 2017/11/2.
 */

public class PermissionsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestAllPermissions();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestAllPermissions() {

        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE};

        for (String permission : permissions) {
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 1);
                break;
            }
        }
    }
}
