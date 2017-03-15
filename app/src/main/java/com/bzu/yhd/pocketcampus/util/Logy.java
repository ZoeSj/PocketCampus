package com.bzu.yhd.pocketcampus.util;

import android.util.Log;

/**
 * 日志管理类
 * </p>
 * @CreateBy Yhd On 2017/3/15 8:37
 */

public final class Logy {

    private static final String TAG = "PocketCompus";
    private static boolean DEBUG;

    public static void init(boolean debug) {
        DEBUG = debug;
    }

    public static void d(String msg) {
        if (DEBUG) Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG) Log.d(tag, msg);
    }

    public static void i(String msg) {
        if (DEBUG) Log.i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG) Log.i(tag, msg);
    }

    public static void w(String msg) {
        if (DEBUG) Log.w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG) Log.w(tag, msg);
    }

    public static void e(String msg) {
        if (DEBUG) Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG) Log.e(tag, msg);
    }
}
