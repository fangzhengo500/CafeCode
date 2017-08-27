package com.luuso.cafecode.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.KeyEvent;

import com.luuso.cafecode.delegate.MainDelegate;
import com.luuso.cafecode.model.Event;
import com.luuso.cafecode.utils.LogUtil;
import com.luuso.cafecode.utils.RxBus;

import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseFrameActivity<MainDelegate> {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static String MENU_CLICK_EVEN = "slid_menu_click_event";

    private Subscription mSubscribe;

    private boolean isOnKeyBacking;
    private Handler mMainLoopHandler = new Handler(Looper.getMainLooper());
    private Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            isOnKeyBacking = false;
            viewDelegate.cancleExit();
        }
    };

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (viewDelegate.menuIsOpen()) {
                viewDelegate.changeMenuState();
            } else if (isOnKeyBacking) {
                mMainLoopHandler.removeCallbacks(onBackTimeRunnable);
                isOnKeyBacking = false;
                finish();
            } else {
                mMainLoopHandler.postDelayed(onBackTimeRunnable, 2000);
                isOnKeyBacking = true;
                viewDelegate.showExitTip();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscribe = RxBus.getDefault().take(Event.class)
                .subscribe(new Action1<Event>() {
                    @Override
                    public void call(Event event) {
                        LogUtil.d(TAG, event.toString());
                        changeContent(event);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainLoopHandler.removeCallbacks(onBackTimeRunnable);

        if (mSubscribe != null) {
            mSubscribe.unsubscribe();
            mSubscribe = null;
        }
    }

    private void changeContent(Event event) {
        viewDelegate.changeMenuState();
    }
}
