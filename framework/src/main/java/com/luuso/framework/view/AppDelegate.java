package com.luuso.framework.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.view
 * @class: AppDelegate
 * @author: 陆伟
 * @Date: 2017/8/27 14:22
 * @desc： 视图层代理的基类
 */

public abstract class AppDelegate implements IDelegate {
    protected SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;

    public abstract int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, null, false);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public Toolbar getToobar() {
        return null;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void initWidget() {

    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T getView(int id) {
        return bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }

        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }
}
