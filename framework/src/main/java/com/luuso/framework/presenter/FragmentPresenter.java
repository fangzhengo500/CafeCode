package com.luuso.framework.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luuso.framework.view.AppDelegate;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.presenter
 * @class: FragmentPresenter
 * @author: 陆伟
 * @Date: 2017/8/27 14:53
 * @desc： TODO
 */

/**
 * Presenter base class for Fragment
 * Presenter层的实现基类
 *
 * @param <T> View delegate class type
 */
public abstract class FragmentPresenter<T extends IDelegate> extends Fragment {
    protected T viewDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            //e.printStackTrace();
            throw new RuntimeException("create IDelegate error" + e.toString());
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
            throw new RuntimeException("create IDelegate error" + e.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewDelegate.create(inflater, container, savedInstanceState);
        return viewDelegate.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDelegate.initWidget();
        bindEvenListener();
    }

    protected void bindEvenListener() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (viewDelegate.getOptionsMenuId() != 0) {
            inflater.inflate(viewDelegate.getOptionsMenuId(), menu);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            //e.printStackTrace();
            throw new RuntimeException("create IDelegate error" + e.toString());
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
            throw new RuntimeException("create IDelegate error" + e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }

    protected abstract Class<T> getDelegateClass();
}
