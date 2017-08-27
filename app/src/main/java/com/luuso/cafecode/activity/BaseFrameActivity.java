package com.luuso.cafecode.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.luuso.cafecode.R;
import com.luuso.framework.presenter.ActivityPresenter;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.cafecode
 * @class: BaseFrameActivity
 * @author: 陆伟
 * @Date: 2017/8/27 19:12
 * @desc： Activity基类
 */

public abstract class BaseFrameActivity<T extends IDelegate> extends ActivityPresenter<T> {
    protected Context mContext;
    protected LinearLayout rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        rootView = new LinearLayout(mContext);
        rootView.setOrientation(LinearLayout.VERTICAL);
        super.onCreate(savedInstanceState);
        super.setContentView(rootView);
    }

    @Override
    protected void initToobar() {
        Toolbar toobar = viewDelegate.getToobar();
        if (toobar == null) {
            toobar = (Toolbar) View.inflate(mContext, R.layout.base_toolbar, null);
            rootView.addView(toobar, 0);
        }
        setSupportActionBar(toobar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(mContext, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        rootView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
