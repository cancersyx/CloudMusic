package com.zsf.netcloudmusic.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.zsf.netcloudmusic.helps.MediaPlayHelper;
import com.zsf.netcloudmusic.models.MusicModel;

/**
 *
 */
public class MusicService extends Service {

    private MediaPlayHelper mMediaPlayHelper;
    private MusicModel mMusicModel;

    public MusicService() {
    }

    public class MusicBind extends Binder{

        /**
         * 设置音乐
         * @param musicModel
         */
        public void setMusic(MusicModel musicModel){
            mMusicModel = musicModel;
        }

        public void playMusic(){
           
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayHelper = MediaPlayHelper.getInstance(this);

    }
}