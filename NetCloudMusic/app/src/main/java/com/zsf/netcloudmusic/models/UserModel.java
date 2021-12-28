package com.zsf.netcloudmusic.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by EWorld
 * 2021/12/28
 */
public class UserModel extends RealmObject {
    @PrimaryKey
    private String phone;//把手机号作为主键
    //不可或缺的值
    @Required
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
