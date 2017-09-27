package com.feng.test;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by feng on 2017/9/6.
 */

public class ToastUtil {
    public static void showToast(Context context) {
        Toast.makeText(context, "oppo", Toast.LENGTH_LONG).show();
    }
}
