package com.lsj.gankapp.ui.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lsj.gankapp.R;
import com.lsj.gankapp.app.ToolBarActivity;
import com.lsj.gankapp.config.Constants;
import com.lsj.gankapp.presenter.MeizhiPresenter;
import com.lsj.gankapp.ui.View.MeizhiView;
import com.lsj.gankapp.utils.DateUtil;
import com.lsj.gankapp.utils.SnackbarUtil;

import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MeizhiActivity extends ToolBarActivity implements MeizhiView {

    @Bind(R.id.meizhi)
    ImageView mMeizhi;


    MeizhiPresenter mPresenter;
    PhotoViewAttacher mAttacher;
    private Bitmap mBitmap;

    private String date;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_meizhi;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MeizhiPresenter(this, this);
        mPresenter.init();

    }

    @Override
    public void initView() {
        String url = getIntent().getStringExtra(Constants.URL);
        date = DateUtil.parseDate(getIntent().getStringExtra(Constants.DATE));

        mAttacher = new PhotoViewAttacher(mMeizhi);
        Glide.with(this)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mMeizhi.setImageBitmap(resource);
                        mAttacher.update();
                        mBitmap = resource;
                    }
                });

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                hideOrShowToolBar();
            }

            @Override
            public void onOutsidePhotoTap() {
                hideOrShowToolBar();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meizhi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                mPresenter.saveImage(mBitmap, date);
                break;
            case R.id.action_share:
                mPresenter.shareImage(mBitmap, date);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showImageResult(String text) {
        SnackbarUtil.showSnackbar(mMeizhi, text);

    }
}
