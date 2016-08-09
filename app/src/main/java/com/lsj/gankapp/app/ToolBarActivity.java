package com.lsj.gankapp.app;

import com.lsj.gankapp.R;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;

import butterknife.Bind;

public abstract class ToolBarActivity extends BaseActivity {

    private boolean isShow = true;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(view -> finish());
        }

    }

    protected void hideOrShowToolBar() {
        mAppBar.animate()
                .translationY(isShow ? -mAppBar.getHeight() : 0)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        isShow = !isShow;
    }
}
