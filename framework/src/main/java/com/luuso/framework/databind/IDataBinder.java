package com.luuso.framework.databind;

import com.luuso.framework.IModel;
import com.luuso.framework.view.IDelegate;

/**
 * @project： CafeCode
 * @package： com.luuso.framework.databind
 * @class: IDataBinder
 * @author: 陆伟
 * @Date: 2017/8/27 15:02
 * @desc： TODO
 */

/**
 * ViewModel实现
 *
 * @param <T>
 * @param <D>
 */
public abstract interface IDataBinder<T extends IDelegate, D extends IModel> {

    /**
     * 将数据与View绑定，这样当数据改变的时候，框架就知道这个数据是和哪个View绑定在一起的，就可以自动改变ui
     * 当数据改变的时候，会回调本方法。
     *
     * @param viewDelegate 视图层代理
     * @param data         数据模型对象
     */
    void viewBindModel(T viewDelegate, D data);
}
