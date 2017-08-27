package com.luuso.cafecode.fragment;

import com.luuso.framework.presenter.FragmentPresenter;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.cafecode.fragment
 * @class: MainFragment
 * @author: 陆伟
 * @Date: 2017/8/27 22:08
 * @desc： TODO
 */

public abstract class MainFragment<T extends IDelegate>
        extends FragmentPresenter<T> {

    public void onChange() {

    }
}
