package com.feng.test;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.feng.fragment.CoordinatorLayoutFragment;
import com.feng.fragment.FullScreenModeFragment;
import com.feng.fragment.RecyclerViewFragment;
import com.feng.fragment.RuntimeDemoFragment;
import com.feng.fragment.SvgDrawableFragment;
import com.feng.fragment.TingFragment;
import com.feng.mvp.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initActionBar();
        startFragment(new RuntimeDemoFragment());

//        immersionBanner();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Intent mLockIntent = new Intent(context, LockScreenActivity.class);
                mLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(mLockIntent);
            }
        }
    };
}
