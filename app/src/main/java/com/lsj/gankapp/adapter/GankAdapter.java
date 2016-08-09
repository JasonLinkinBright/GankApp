package com.lsj.gankapp.adapter;

import com.lsj.gankapp.R;
import com.lsj.gankapp.config.Constants;
import com.lsj.gankapp.model.Gank;
import com.lsj.gankapp.ui.Activity.WebViewActivity;
import com.lsj.gankapp.utils.DateUtil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ClassName: GankAdapter
 * Desc:
 * Created by lsj on 16/7/28.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    private List<Gank> mList;
    private Context context;

    public GankAdapter(Context context, List<Gank> list) {
        this.context = context;
        if (list == null) {
            list = new ArrayList<>();
        }
        mList = list;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {
        Gank gank = mList.get(position);
        holder.view.setTag(gank);
        holder.mTitle.setText(gank.getDesc());
        holder.mWho.setText(gank.getWho());
        holder.mDate.setText(DateUtil.parseDate(gank.getPublishedAt()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void refreshData(List<Gank> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List<Gank> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    class GankViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.card_view)
        CardView mCardView;
        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.who)
        TextView mWho;
        @Bind(R.id.date)
        TextView mDate;

        View view;

        public GankViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_view)
        public void onClick() {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.URL, ((Gank) view.getTag()).getUrl());
            intent.putExtra(Constants.DES,((Gank) view.getTag()).getDesc());
            context.startActivity(intent);

        }

    }
}
