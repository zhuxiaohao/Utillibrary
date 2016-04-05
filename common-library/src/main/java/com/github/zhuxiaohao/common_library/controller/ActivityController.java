/**
 * Project Name:androidTools
 * File Name:ActivityController.java
 * Package Name:com.zhuxiaohao.common.activitycontroller
 * Date:2015-4-23下午9:39:02
 * Copyright (c) 2015, zhuxiaohao All Rights Reserved.
 */

package com.github.zhuxiaohao.common_library.controller;


import android.app.Activity;

import com.github.zhuxiaohao.common_library.util.Log;

import java.util.List;

/**
 * ClassName:ActivityController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-4-23 下午9:39:02 <br/>
 *
 * @author chenhao
 * @see 管理 Activity
 * @since JDK 1.6
 */
public class ActivityController {
    /**
     * 实列
     */
    public static List<Activity> list = new java.util.ArrayList<Activity>();

    /**
     * addActivity:(添加 activity). <br/>
     *
     * @param activity
     * @author chenhao
     * @since JDK 1.6
     */
    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    /**
     * removeActivity:(移除 activity). <br/>
     *
     * @param activity
     * @author chenhao
     * @since JDK 1.6
     */
    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    /**
     * finshAll:(删除所有活动的 activity). <br/>
     *
     * @author chenhao
     * @since JDK 1.6
     */
    public static void finshAll() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }

        }

    }

    /**
     * 获取当前 app 可用内存
     *
     * @param c
     * @param s
     */
    private static void displayBriefMemory(android.content.Context c, String s) {
        final android.app.ActivityManager activityManager = (android.app.ActivityManager) c.getSystemService(android.content.Context.ACTIVITY_SERVICE);
        android.app.ActivityManager.MemoryInfo info = new android.app.ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.i(s, "系统剩余内存:" + (info.availMem >> 10) + "k");
        Log.i(s, "系统是否处于低内存运行：" + info.lowMemory);
        Log.i(s, "当系统剩余内存低于" + info.threshold + "时就看成低内存运行");
    }
}
