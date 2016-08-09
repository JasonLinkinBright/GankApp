package com.lsj.gankapp.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * ClassName: MyRecyclerView
 * Desc:
 * Created by lsj on 16/7/28.
 */
public class MyRecyclerView extends RecyclerView {
    private LoadMoreListener mListener;

    private int lastVisibleItemPosition;


    public void setListener(LoadMoreListener listener) {
        mListener = listener;
    }

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPosition = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPosition);
            lastVisibleItemPosition = getMax(lastPosition);

        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        RecyclerView.LayoutManager layoutManager =  getLayoutManager();
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            int totalCount = layoutManager.getItemCount();
            if (lastVisibleItemPosition == totalCount - 1) {
                mListener.loadMore();

            }
        }
    }

    private int getMax(int[] lastPosition) {
        int max = lastPosition[0];
        for (int value : lastPosition) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public interface LoadMoreListener {
        void loadMore();
    }
}
