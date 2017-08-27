package com.luuso.cafecode.utils;

import android.util.Log;

/**
 * @project： CafeCode
 * @package： com.luuso.cafecode.utils
 * @class: LogUtil
 * @author: 陆伟
 * @Date: 2017/8/27 23:08
 * @desc： TODO
 */

public class LogUtil {
    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}
