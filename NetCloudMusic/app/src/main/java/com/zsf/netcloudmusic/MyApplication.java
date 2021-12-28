package com.zsf.netcloudmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

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
        //Realm数据库
        Realm.init(this);
    }
}
