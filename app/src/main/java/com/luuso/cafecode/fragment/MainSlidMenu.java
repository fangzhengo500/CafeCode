package com.luuso.cafecode.fragment;

import android.view.View;

import com.luuso.cafecode.R;
import com.luuso.cafecode.activity.MainActivity;
import com.luuso.cafecode.delegate.MainSlidMenuDelegate;
import com.luuso.cafecode.model.Event;
import com.luuso.cafecode.utils.LogUtil;
import com.luuso.cafecode.utils.RxBus;
import com.luuso.framework.presenter.FragmentPresenter;


/**
 * @project： CafeCode
 * @package： com.luuso.cafecode.fragment
 * @class: MainSlidMenu
 * @author: 陆伟
 * @Date: 2017/8/27 22:11
 * @desc： TODO
 */

public class MainSlidMenu extends FragmentPresenter<MainSlidMenuDelegate> implements View.OnClickListener {
    private static final String TAG = MainSlidMenuDelegate.class.getSimpleName();

    @Override
    protected Class getDelegateClass() {
        return MainSlidMenuDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,
                R.id.menu_item_tag1,
                R.id.menu_item_tag2,
                R.id.menu_item_tag3,
                R.id.menu_item_tag4);
    }

    @Override
    public void onClick(View view) {
        LogUtil.v(TAG, "onClick: view = " + view);
        Event event = new Event();
        event.setAction(MainActivity.MENU_CLICK_EVEN);
        event.setObject(view);
        RxBus.getDefault().post(event);
    }
}
