package com.zsf.netcloudmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author EWorld  e-mail:852333743@qq.com
 * 2019/3/18
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //AndroidUtilCode
        Utils.init(this);
    }
}
