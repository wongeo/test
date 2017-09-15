package com.zhangyue.iReader.app;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by feng on 2017/9/14.
 */

public class APP {

    private static Context mAppContext;

    public static Context getAppContext() {
        return mAppContext;
    }

    public static void setmAppContext(Context context) {
        mAppContext = context;
    }

    public static String a() {
        return "i519782175";
//        return "i454319337";
    }


}
