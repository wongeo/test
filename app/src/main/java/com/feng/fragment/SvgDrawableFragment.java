package com.feng.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.mvp.BaseFragment;
import com.feng.test.R;
import com.feng.test.ToastUtil;

/**
 * Created by feng on 2017/9/7.
 */

public class SvgDrawableFragment extends BaseFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.svg_drawable_fragment, container, false);
        mView = view.findViewById(R.id.button);

        mView.setClickable(true);
        mView.setBackground(tintDrawable(getContext(), R.drawable.ic_delete_menu_svg, R.color.voice_svg_1));
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(getActivity());
            }
        });
        return view;
    }

    public static Drawable tintDrawable(@NonNull Context context, @DrawableRes int res, @ColorRes int colorResId) {
        ColorStateList colorStateList = context.getColorStateList(colorResId);
        Drawable drawable = ContextCompat.getDrawable(context, res).mutate();
        DrawableCompat.setTintList(drawable, colorStateList);
        return drawable;
    }
}
