package com.zsf.netcloudmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.LoginActivity;

/**
 * Created by EWorld
 * 2021/12/23
 */
public class UserUtils {

    /**
     * 验证用户输入合法性
     *
     * @param context
     * @param phone
     * @param password
     * @return
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent标识，清理task栈并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //必须设置到startActivity之后执行
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }
}
