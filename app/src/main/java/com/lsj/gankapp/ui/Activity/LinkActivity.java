package com.lsj.gankapp.ui.Activity;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.lsj.gankapp.R;
import com.lsj.gankapp.app.ToolBarActivity;
import com.lsj.gankapp.presenter.LinkPresenter;
import com.lsj.gankapp.ui.View.ILinkView;

import butterknife.Bind;

/**
 * Created by lsj on 16/8/5.
 */
public class LinkActivity extends ToolBarActivity implements ILinkView{


    @Bind(R.id.author_content)
    TextView mAuthorContent;
    @Bind(R.id.author_mail)
    TextView mAuthorMail;
    @Bind(R.id.author_csdn)
    TextView mAuthorCsdn;
    private LinkPresenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_link;
    }

    @Override
    protected void initPresenter() {
        mPresenter=new LinkPresenter(this,this);
        mPresenter.init();
    }

    @Override
    public void LinkSucceed() {

    }

    @Override
    public void LinkFailed() {

    }

    @Override
    public void initView() {
        mAuthorContent.setText(getResources().getString(R.string.author_content));

        mAuthorMail.setText("联系邮箱： lsjxxsn_1234@163.com");

        String  str = "CSDN博客： <a href='http://blog.csdn.net/linshijun33'>blog.csdn.net/linshijun33</a>";
        mAuthorCsdn.setText(Html.fromHtml(str));
        mAuthorCsdn.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
