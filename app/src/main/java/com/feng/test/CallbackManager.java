package com.feng.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by feng on 2018/3/9.
 */

public class CallbackManager {

    private static CallbackManager instance = new CallbackManager();

    private List<ICallback> mCallbacks = new ArrayList<>();

    public static CallbackManager getInstance() {
        return instance;
    }

    public void addCallback(ICallback callback) {
        mCallbacks.add(callback);

        Collections.sort(mCallbacks, new Comparator<ICallback>() {
            @Override
            public int compare(ICallback o1, ICallback o2) {
                return o2.level() - o1.level();
            }
        });

    }

    public void removeCallback(ICallback callback) {
        mCallbacks.remove(callback);
    }

    public void run() {
        for (ICallback callback : mCallbacks) {
            callback.run();
        }
    }
}
