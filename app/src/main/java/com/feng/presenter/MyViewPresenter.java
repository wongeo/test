package com.feng.presenter;

import com.feng.mvp.BasePresenter;
import com.feng.mvp.BaseView;
import com.feng.mvp.BaseViewPresenter;
import com.feng.view.MyCustomView;

/**
 * Created by wj148202 on 2017/10/30.
 */

public class MyViewPresenter extends BaseViewPresenter<MyCustomView> {
    public MyViewPresenter(MyCustomView view) {
        super(view);
    }
}
