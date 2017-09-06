package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.feng.test.R;

/**
 * Created by feng on 2017/7/5.
 */

public class TingFragment extends Fragment implements View.OnClickListener {

    private Fragment mTempFragment;
    private WebViewFragment mWebFragment;
    private RecyclerViewFragment mListFragment;

    Button mButton1;
    Button mButton2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ting_fragment, container, false);
        mWebFragment = new WebViewFragment();
        mListFragment = new RecyclerViewFragment("title");
        mButton1 = (Button) view.findViewById(R.id.left_tab);
        mButton2 = (Button) view.findViewById(R.id.middle_tab);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

        return view;
    }


    private void switchFragment(Fragment fragment) {
        if (fragment != mTempFragment) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (mTempFragment != null) {
                transaction.hide(mTempFragment);
            }

            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).commitNow();
            } else {
                transaction.show(fragment).commitNow();
            }
            mTempFragment = fragment;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            switchFragment(mListFragment);
        } else if (v == mButton2) {
            switchFragment(mWebFragment);
        }
    }
}
