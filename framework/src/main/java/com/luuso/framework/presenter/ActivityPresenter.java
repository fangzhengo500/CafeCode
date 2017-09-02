package com.luuso.framework.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.presenter
 * @class: ActivityPresenter
 * @author: 陆伟
 * @Date: 2017/8/27 14:32
 * @desc： TODO
 */

/**
 * Presenter base class for Activity
 * Presenter层的实现基类
 *
 * @param <T> View delegate class type
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {
    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            //e.printStackTrace();
            throw new RuntimeException("create IDelegate error" + e.toString());
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
            throw new RuntimeException("create IDelegate error" + e.toString());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(viewDelegate.getRootView());
        initToobar();
        viewDelegate.initWidget();
        bindEvenListener();
    }

    protected void initToobar() {
        Toolbar toobar = viewDelegate.getToolbar();
        if (toobar != null) {
            setSupportActionBar(toobar);
        }
    }

    protected void bindEvenListener() {
    }

    protected void initToolbar() {
        Toolbar toolbar = viewDelegate.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate != null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                //e.printStackTrace();
                throw new RuntimeException("create IDelegate error" + e.toString());
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
                throw new RuntimeException("create IDelegate error" + e.toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }

    protected abstract Class<T> getDelegateClass();
}
