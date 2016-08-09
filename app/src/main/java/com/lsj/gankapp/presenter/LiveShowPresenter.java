package com.lsj.gankapp.presenter;

import android.content.Context;

import com.lsj.gankapp.app.BasePresenter;
import com.lsj.gankapp.ui.View.ILiveShowView;

/**
 * ClassName: LiveShowPresenter
 * Desc:
 * Created by lsj on 2016/8/1.
 */
public class LiveShowPresenter extends BasePresenter<ILiveShowView> {
    public LiveShowPresenter(Context context, ILiveShowView view) {
        super(context, view);
    }

    public void loadVideo(){
        viewImpl.startLiveShow();
    }
}
