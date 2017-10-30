package com.feng.presenter;

import com.feng.mvp.BasePresenter;
import com.feng.view.MyCustomView;

/**
 * Created by wj148202 on 2017/10/30.
 */

public class MyViewPresenter extends BasePresenter<MyCustomView> {
    public MyViewPresenter(MyCustomView view) {
        super(view);
    }

    public void abd(){
        getView().isActivated()
    }
}
