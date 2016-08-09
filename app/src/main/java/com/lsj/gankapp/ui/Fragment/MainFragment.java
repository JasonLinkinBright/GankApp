package com.lsj.gankapp.ui.Fragment;


import com.orhanobut.logger.Logger;
import com.lsj.gankapp.R;
import com.lsj.gankapp.adapter.GankAdapter;
import com.lsj.gankapp.adapter.MeizhiAdapter;
import com.lsj.gankapp.app.BaseTabFragment;
import com.lsj.gankapp.config.Constants;
import com.lsj.gankapp.model.Gank;
import com.lsj.gankapp.presenter.MainFragmentPresenter;
import com.lsj.gankapp.ui.View.MainFragmentView;
import com.lsj.gankapp.ui.widget.MyRecyclerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseTabFragment implements MainFragmentView, SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM = "title";
    @Bind(R.id.recycler_view)
    MyRecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String type;
    private MainFragmentPresenter mPresenter;

    private MeizhiAdapter meizhiAdapter;
    private GankAdapter gankAdapter;

    private int page = 1;
    private boolean isRefresh;
    private boolean isInitView = false;
    private boolean isLoadOnce = false;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String type) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_PARAM);
            Logger.d(type);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        super.onCreateView(inflater, container, savedInstanceState);
        isInitView = true;
        lazyLoad();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainFragmentPresenter(fragmentActivity, this);
        mPresenter.init();

    }

    @Override
    protected void lazyLoad() {
        if (!isInitView || !isVisible || isLoadOnce) {
            return;
        }
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            getData();
            isInitView = false;
        });
    }

    @Override
    public void initView() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        if (type.equals(Constants.TYPE_MEIZHI)) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
            meizhiAdapter = new MeizhiAdapter(fragmentActivity);
            mRecyclerView.setAdapter(meizhiAdapter);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            gankAdapter = new GankAdapter(fragmentActivity, null);
            mRecyclerView.setAdapter(gankAdapter);
        }

        mRecyclerView.setListener(this::getData);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showProgress(boolean show) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(show);
        }
    }

    @Override
    public void showListView(List<Gank> list) {
        page++;
        isLoadOnce = true;
        if (isRefresh) {
            meizhiAdapter.refreshData(list);
            isRefresh = false;
        } else {
            meizhiAdapter.addData(list);
        }

    }

    @Override
    public void refreshGankList(List<Gank> list) {
        page++;
        isLoadOnce = true;
        if (isRefresh) {
            gankAdapter.refreshData(list);
            isRefresh = false;
        } else {
            gankAdapter.addData(list);
        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        getData();
    }

    private void getData() {
        if (type.equals(Constants.TYPE_MEIZHI)) {
            mPresenter.getMeizhi(page);
        } else {
            mPresenter.getGank(type, page);
        }
    }
}
