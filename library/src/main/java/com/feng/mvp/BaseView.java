package com.feng.mvp;

/**
 * MVP View基类
 *
 * @author adison
 * @date 16/9/19
 * @time 上午10:57
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    
}
