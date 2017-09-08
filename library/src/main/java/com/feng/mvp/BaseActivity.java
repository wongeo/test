package com.feng.mvp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by feng on 2017/4/6.
 */
public class BaseActivity extends AppCompatActivity {

    private Fragment mFragment;

    protected void startFragment(BaseFragment fragment) {
        mFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    protected void startFragment(Fragment fragment) {
        mFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
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

    protected void immersionBanner() {
        if (getActionBar() != null) {
            getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        /*
            1.设置透明状态栏
            2.设置ContentView的setFitsSystemWindows为true
            3.设置ContentView的第一个子View的setFitsSystemWindows为false,意思为不为系统view留空间
         */
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
        ViewGroup viewGroup = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        View childView = viewGroup.getChildAt(0);
        if (null != childView) {
            ViewCompat.setFitsSystemWindows(childView, false);//设置ContentView的第一个子View的setFitsSystemWindows为false,意思为不为系统view留空间
        }
    }
}
