package com.luuso.cafecode.utils;


import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @project： CafeCode
 * @package： com.luuso.cafecode.utils
 * @class: RxBus
 * @author: 陆伟
 * @Date: 2017/8/27 22:52
 * @desc： TODO
 */

public class RxBus {
    private static final String TAG = RxBus.class.getSimpleName();

    private static volatile RxBus mInstance;

    private RxBus() {
    }

    public static RxBus getDefault() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object event) {
        if (event == null) {
            throw new NullPointerException("event is null");
        }
        bus.onNext(event);
    }

    public <T> Observable<T> take(final Class<T> eventType) {
        return bus.filter(new Func1<Object, Boolean>() {

            @Override
            public Boolean call(Object o) {
                boolean instance = eventType.isInstance(o);
                LogUtil.v(TAG, "call: eventType = " + eventType + "\n o = " + o + "\n instance = " + instance);
                return instance;
            }
        }).cast(eventType);
    }
}
