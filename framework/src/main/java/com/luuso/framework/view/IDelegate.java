package com.luuso.framework.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.view
 * @class: IDelegate
 * @author: 陆伟
 * @Date: 2017/8/27 14:19
 * @desc： 视图层代理的接口协议
 * @desc：View delegate base class
 */

public interface IDelegate {
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    int getOptionsMenuId();

    Toolbar getToolbar();

    View getRootView();

    void initWidget();
}
