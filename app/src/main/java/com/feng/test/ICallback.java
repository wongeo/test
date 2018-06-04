package com.feng.test;

/**
 * Created by feng on 2017/12/23.
 */

interface ICallback {

    /**
     * 层级,数值越大，优先执行
     *
     * @return
     */
    int level();

    /**
     * 运行
     *
     * @return
     */
    int run();

    /**
     * 是否拦截
     *
     * @return
     */
    boolean intercept();
}