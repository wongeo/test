package com.feng.mvp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.feng.library.R;
import com.feng.util.StatusBarUtil;


/**
 * Created by feng on 2017/4/6.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestAllPermissions();
        }
    }

    private Fragment mFragment;

    protected void startFragment(BaseFragment fragment) {
        mFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, fragment);
        transaction.commit();
    }

    protected void startFragment(Fragment fragment) {
        mFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(android.R.id.content, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mFragment instanceof BaseFragment) {
            if (((BaseFragment) mFragment).onBackPress()) {
                return;
            }
        }
        super.onBackPressed();
    }

    protected void initActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getActionBar() != null) {
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryaaa)));
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        StatusBarUtil.setStatusBarColorKITKAT(this, Color.argb(200, 255, 0, 0));

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryaaa));
//        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                int grantResult = grantResults[i];
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    //授权成功后的逻辑
                } else {
                    //授权失败逻辑
                    finish();
                }
            }
        }
    }
}
