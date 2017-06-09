package com.feng.mvp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by feng on 2017/4/6.
 */
public class BaseActivity extends AppCompatActivity {

    private BaseFragment mFragment;

    protected void startFragment(BaseFragment fragment) {
        mFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mFragment.onBackPress()) {
            return;
        }
        super.onBackPressed();
    }
}
