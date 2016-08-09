package com.lsj.gankapp.ui.View;

import com.lsj.gankapp.app.BaseView;
import com.lsj.gankapp.model.Gank;

import java.util.List;

/**
 * ClassName: MainFragmentView
 * Desc:
 * Created by lsj on 16/7/28.
 */

public interface MainFragmentView extends BaseView {

    void showProgress(boolean show);

    void showListView(List<Gank> list);

    void refreshGankList(List<Gank> list);
}
