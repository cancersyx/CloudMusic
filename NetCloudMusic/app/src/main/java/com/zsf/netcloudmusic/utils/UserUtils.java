package com.zsf.netcloudmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.LoginActivity;
import com.zsf.netcloudmusic.helps.RealmHelp;
import com.zsf.netcloudmusic.models.UserModel;

import java.util.List;

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

    /**
     * @param context
     */
    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent标识，清理task栈并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //必须设置到startActivity之后执行
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

    /**
     * 注册用户验证
     *
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context, String phone, String password, String passwordConfirm) {
        //手机号
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        //密码
        if (StringUtils.isEmpty(password) || !password.equals(passwordConfirm)) {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        //用户输入的手机号是否已经注册 TODO: 2021/12/28
        if (UserUtils.userExistFromPhone(phone)) {
            Toast.makeText(context, "手机号已经注册过了", Toast.LENGTH_SHORT).show();
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户到数据库
     *
     * @param userModel
     */
    public static void saveUser(UserModel userModel) {
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();

    }

    public static boolean userExistFromPhone(String phone) {
        boolean result = false;
        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> allUser = realmHelp.getAllUser();
        for (UserModel userModel : allUser) {
            if (userModel.getPhone().equals(phone)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
