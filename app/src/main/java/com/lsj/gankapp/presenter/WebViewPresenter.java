package com.lsj.gankapp.presenter;

import com.lsj.gankapp.app.BasePresenter;
import com.lsj.gankapp.ui.View.IWebView;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * ClassName: WebViewPresenter
 * Desc:
 * Created by lsj on 16/7/28.
 */
public class WebViewPresenter extends BasePresenter<IWebView> {
    public WebViewPresenter(Context context, IWebView view) {
        super(context, view);
    }

    public void loadWeb(android.webkit.WebView webView, String url){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.loadUrl(url);
    }

    public void refresh(WebView webView){
        webView.reload();

    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    class MyWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(android.webkit.WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            viewImpl.showProgress(newProgress);
        }
    }
}
