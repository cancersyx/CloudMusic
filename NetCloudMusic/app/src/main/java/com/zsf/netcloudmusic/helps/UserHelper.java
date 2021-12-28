package com.zsf.netcloudmusic.helps;

/**
 * Created by EWorld
 * 2021/12/28
 * <p>
 * 一、用户登录
 * 1.
 * <p>
 * 二、用户退出登录
 */
public class UserHelper {

    private static UserHelper instance;

    public static UserHelper getInstance() {
        if (instance == null) {
            synchronized (UserHelper.class) {
                if (instance == null) {
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }

    private UserHelper() {
    }


    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
