package com.zsf.netcloudmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.zsf.netcloudmusic.constant.SPConstant;
import com.zsf.netcloudmusic.helps.UserHelper;

/**
 * Created by EWorld
 * 2021/12/28
 */
public class SPUtils {

    /**
     * 保存登录用户标记(手机号)
     *
     * @param context
     * @param phone
     * @return
     */
    public static boolean saveUser(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(SPConstant.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SPConstant.SP_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;

    }

    /**
     * 验证是否存在已经登录用户
     *
     * @param context
     * @return
     */
    public static boolean isLoginUser(Context context) {
        boolean result = false;
        SharedPreferences preferences = context.getSharedPreferences(SPConstant.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = preferences.getString(SPConstant.SP_KEY_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            result = true;
            UserHelper.getInstance().setPhone(phone);
        }

        return result;
    }

    /**
     * 删除用户标记
     * @param context
     * @return
     */
    public static boolean removeUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SPConstant.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SPConstant.SP_KEY_PHONE);
        boolean result = editor.commit();
        return result;


    }
}
