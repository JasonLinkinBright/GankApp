package com.lsj.gankapp.app;

import android.content.Context;

/**
 * ClassName: BasePresenter
 * Desc:
 * Created by lsj on 16/7/28.
 */

public class BasePresenter<T extends BaseView> {

    public Context context;

    public T viewImpl;

    public BasePresenter(Context context, T view) {
        this.context = context;
        this.viewImpl = view;
    }

    public void init(){
        viewImpl.initView();
    }
}
