package com.zhangyue.iReader.app;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by feng on 2017/9/15.
 */

public class DeviceInfor {

    private static String mIMEI;


    public static String getDeviceId() {
        if (TextUtils.isEmpty(mIMEI)) {
            try {
                TelephonyManager tm = (TelephonyManager) APP.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
                mIMEI = tm.getDeviceId(); //设备imei
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mIMEI;
    }
}
