// IMyAidlInterface.aidl
package com.feng.aidl;

// Declare any non-default types here with import statements
import android.content.Intent;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void nav(in Intent intent);
}
