package com.lsj.gankapp.app;

import com.orhanobut.logger.Logger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    
    protected final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initPresenter();
        Logger.i("onCreate");
    }

    protected abstract int getLayoutResId();
    protected abstract void initPresenter();

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        Logger.i(TAG,"onDestroy");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i(TAG,"onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i(TAG,"onPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.i(TAG,"onRestart");

    }
}
