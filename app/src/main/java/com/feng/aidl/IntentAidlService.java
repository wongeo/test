/*
 * Copyright (c) 2017. shixinzhang (shixinzhang2016@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.feng.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Description:
 * <br> 使用 AIDL 通信的服务端
 * <p>
 * <br> Created by shixinzhang on 17/5/18.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

public class IntentAidlService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    /**
     * 继承生成的本地 Binder ，实现 AIDL 制定的方法
     */
    private IBinder mIBinder = new IMyAidlInterface.Stub() {

        @Override
        public void nav(Intent intent) throws RemoteException {
            Log.d("IMyAidlInterface", "IMyAidlInterface:"+intent.hashCode());
        }
    };

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
