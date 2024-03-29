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
import com.zsf.netcloudmusic.helps.RealmHelper;
import com.zsf.netcloudmusic.helps.UserHelper;
import com.zsf.netcloudmusic.models.UserModel;

import java.util.List;

/**
 * Created by EWorld
 * 2021/12/23
 */
public class UserUtils {

    /**
     * 验证登录用户
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

        /**
         * 1.用户当前手机号是否被注册
         * 2.用户输入的手机号和密码是否匹配
         * 3.
         */
        if (!UserUtils.userExistFromPhone(phone)) {
            Toast.makeText(context, "当前手机号未注册", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelper realmHelper = new RealmHelper();
        boolean result = realmHelper.validateUser(phone, EncryptUtils.encryptMD5ToString(password));
        if (!result) {
            Toast.makeText(context, "手机或密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }

        //保存用户登录标记
        boolean isSave = SPUtils.saveUser(context, phone);
        if (!isSave) {
            Toast.makeText(context, "系统错误，请稍后重试", Toast.LENGTH_SHORT).show();
            return false;
        }

        //保存用户标记，在全局单例类中
        UserHelper.getInstance().setPhone(phone);

        //保存音乐源数据
        realmHelper.setMusicSource(context);
        realmHelper.close();//记得及时关闭

        return true;
    }


    /**
     * 退出登录
     *
     * @param context
     */
    public static void logout(Context context) {
        //删除SharedPreferences 保存的用户标记
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove) {
            Toast.makeText(context, "系统错误，请稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        //删除音乐数据源
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.removeMusicSource();
        realmHelper.close();

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
        //用户输入的手机号是否已经注册
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
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveUser(userModel);
        realmHelper.close();

    }

    /**
     * 根据手机号判断用户是否存在
     *
     * @param phone
     * @return
     */
    public static boolean userExistFromPhone(String phone) {
        boolean result = false;
        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> allUser = realmHelper.getAllUser();
        for (UserModel userModel : allUser) {
            if (userModel.getPhone().equals(phone)) {
                result = true;
                break;
            }
        }
        realmHelper.close();
        return result;
    }

    /**
     * 验证是否存在已经登录用户
     *
     * @param context
     * @return
     */
    public static boolean validateUserLogin(Context context) {
        return SPUtils.isLoginUser(context);
    }

    /**
     * 修改密码
     *
     * @param context
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     * @return
     */
    public static boolean changePassword(Context context, String oldPwd, String newPwd, String confirmPwd) {
        if (TextUtils.isEmpty(oldPwd)) {
            Toast.makeText(context, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(newPwd) || !newPwd.equals(confirmPwd)) {
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        //验证原密码是否正确
        RealmHelper realmHelper = new RealmHelper();
        UserModel userModel = realmHelper.getUser();
        if (!EncryptUtils.encryptMD5ToString(oldPwd).equals(userModel.getPassword())) {
            Toast.makeText(context, "原密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelper.changePassword(EncryptUtils.encryptMD5ToString(newPwd));

        realmHelper.close();

        return true;
    }
}
