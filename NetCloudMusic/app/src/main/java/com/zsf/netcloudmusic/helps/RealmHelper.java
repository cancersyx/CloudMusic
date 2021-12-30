package com.zsf.netcloudmusic.helps;

import android.content.Context;

import com.zsf.netcloudmusic.migration.Migration;
import com.zsf.netcloudmusic.models.AlbumModel;
import com.zsf.netcloudmusic.models.MusicModel;
import com.zsf.netcloudmusic.models.MusicSourceModel;
import com.zsf.netcloudmusic.models.UserModel;
import com.zsf.netcloudmusic.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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
     * 迁移数据
     */
    public static void migration() {
        RealmConfiguration configuration = getRealmConf();
        //Realm设置最新配置
        Realm.setDefaultConfiguration(configuration);
        //告诉Realm需要进行数据迁移
        try {
            Realm.migrateRealm(configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realm数据库发生结构性变化就需要对数据库进行迁移(升级)
     *
     * @return
     */
    private static RealmConfiguration getRealmConf() {
        return new RealmConfiguration.Builder().schemaVersion(1).migration(new Migration()).build();
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

    /**
     * @return
     */
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
     * 获取当前用户
     *
     * @return
     */
    public UserModel getUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * 修改密码
     *
     * @param password
     */
    public void changePassword(String password) {
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }

    /**
     * 1.用户登录，保存数据
     * 2.用户退出，删除数据
     */
    /**
     * 保存音乐源数据
     *
     * @param context
     */
    public void setMusicSource(Context context) {
        String musicSourceJson = DataUtils.getJsonFromAssets(context, "DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class, musicSourceJson);
        mRealm.commitTransaction();

    }

    /**
     * 删除音乐数据
     */
    public void removeMusicSource() {
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }

    /**
     * 返回音乐源数据
     *
     * @return
     */
    public MusicSourceModel getMusicSource() {
        return mRealm.where(MusicSourceModel.class).findFirst();
    }

    /**
     * 返回歌单
     *
     * @param albumId
     * @return
     */
    public AlbumModel getAlbum(String albumId) {
        return mRealm.where(AlbumModel.class).equalTo("albumId", albumId).findFirst();
    }

    /**
     * 返回歌曲
     * @param musicId
     * @return
     */
    public MusicModel getMusic(String musicId) {
        return mRealm.where(MusicModel.class).equalTo("musicId", musicId).findFirst();
    }
}
