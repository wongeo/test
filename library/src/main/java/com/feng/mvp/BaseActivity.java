package com.feng.mvp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        if (getActionBar() != null) {
            getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
}
