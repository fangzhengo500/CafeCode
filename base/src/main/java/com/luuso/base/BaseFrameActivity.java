package com.luuso.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.luuso.framework.presenter.ActivityPresenter;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.base
 * @class: BaseFrameActivity
 * @author: 陆伟
 * @Date: 2017/9/2 14:00
 * @desc： TODO
 */

public abstract class BaseFrameActivity<T extends IDelegate> extends ActivityPresenter<T> {
    protected LinearLayout rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);
        super.onCreate(savedInstanceState);
        super.setContentView(rootView);
    }

    @Override
    protected void initToolbar() {
        Toolbar toolbar = viewDelegate.getToolbar();
        if (toolbar == null) {
            toolbar = (Toolbar) View.inflate(this, R.layout.base_toolbar, null);
            rootView.addView(toolbar, 0);
        }
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
