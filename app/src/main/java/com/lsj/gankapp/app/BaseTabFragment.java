package com.lsj.gankapp.app;

/**
 * ClassName: BaseTabFragment
 * Desc:
 * Created by lsj on 16/7/28.
 */
public abstract class BaseTabFragment extends BaseFragment {

    protected boolean isVisible = false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();

        } else {
            isVisible = false;
        }
    }

    protected abstract void lazyLoad();

}
