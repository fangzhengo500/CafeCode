package com.luuso.cafecode.delegate;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.luuso.cafecode.R;
import com.luuso.cafecode.utils.AnimationUtil;
import com.luuso.framework.view.AppDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.cafecode.delegate
 * @class: MainDelegate
 * @author: 陆伟
 * @Date: 2017/8/27 19:34
 * @desc： TODO
 */

public class MainDelegate extends AppDelegate {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Toolbar getToolbar() {
        mToolbar = getView(R.id.toolbar);
        if (mToolbar == null) {
            throw new NullPointerException("Must include Toolbar and define @+id/toolbar." +
                    " You can newViewHolder @layout/base_toolbar");
        }
        return mToolbar;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        AppCompatActivity activity = getActivity();
        mDrawerLayout = getView(R.id.drawer_layout);
        //设置显示home图标
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setHomeButtonEnabled(true); //设置返回键可用
            actionBar.setDisplayHomeAsUpEnabled(true);
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity,
                    mDrawerLayout, mToolbar, R.string.open, R.string.close);
            drawerToggle.syncState();
            mDrawerLayout.setDrawerListener(drawerToggle);
        }
    }

    public boolean menuIsOpen() {
        return mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    public boolean changeMenuState() {
        boolean isOpen = menuIsOpen();
        if (isOpen) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }else {
            mDrawerLayout.openDrawer(Gravity.START);
        }
        return !isOpen;
    }

    /**
     * 显示Toolbar的退出tip
     */
    public void showExitTip() {
        TextView view = getView(R.id.titlebar_text_exittip);
        view.setVisibility(View.VISIBLE);
        Animation a = AnimationUtil.getTranslateAnimation(0f, 0f, -mToolbar.getHeight(), 0f, 300);
        view.startAnimation(a);
    }

    /**
     * 取消退出
     */
    public void cancleExit() {
        final TextView view = getView(R.id.titlebar_text_exittip);
        Animation a = AnimationUtil.getTranslateAnimation(0f, 0f, 0f, -mToolbar.getHeight(), 300);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        view.startAnimation(a);
    }


}
