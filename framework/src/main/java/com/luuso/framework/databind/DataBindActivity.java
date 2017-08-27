package com.luuso.framework.databind;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.luuso.framework.IModel;
import com.luuso.framework.presenter.ActivityPresenter;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.databind
 * @class: DataBindActivity
 * @author: 陆伟
 * @Date: 2017/8/27 15:16
 * @desc： TODO
 */

public abstract class DataBindActivity<T extends IDelegate> extends ActivityPresenter<T> {
    protected IDataBinder binder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binder = getDataBinder();
    }

    protected abstract IDataBinder getDataBinder();

    @SuppressWarnings("unchecked")
    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null) {
            binder.viewBindModel(viewDelegate, data);
        }
    }
}
