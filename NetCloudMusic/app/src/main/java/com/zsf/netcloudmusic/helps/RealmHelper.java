package com.zsf.netcloudmusic.helps;

import com.zsf.netcloudmusic.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by EWorld
 * 2021/12/28
 */
public class RealmHelper {
    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Realm采用引用计数，要在使用结束后及时关闭
     */
    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /**
     * 保存用户
     *
     * @param userModel
     */
    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }

    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    /**
     * 验证用户信息
     *
     * @param phone
     * @param password
     * @return
     */
    public boolean validateUser(String phone, String password) {
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone", phone).equalTo("password", password);
        UserModel userModel = query.findFirst();
        if (userModel != null) {
            result = true;
        }
        return result;
    }

    /**
     *  获取当前用户
     * @return
     */
    public UserModel getUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * 修改密码
     * @param password
     */
    public void changePassword(String password){
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }
}
