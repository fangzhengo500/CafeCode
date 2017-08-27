package com.luuso.framework.databind;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.luuso.framework.IModel;
import com.luuso.framework.presenter.FragmentPresenter;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.databind
 * @class: DataBindFragment
 * @author: 陆伟
 * @Date: 2017/8/27 15:22
 * @desc： TODO
 */

public abstract class DataBindFragment<T extends IDelegate> extends FragmentPresenter<T> {
    protected IDataBinder binder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = getDataBinder();
    }

    public abstract IDataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null)
            binder.viewBindModel(viewDelegate, data);
    }
}
