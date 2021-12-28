package com.zsf.netcloudmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by EWorld
 * 2021/12/28
 */
public class MediaPlayHelper {
    private static MediaPlayHelper instance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private String mPath;

    public static MediaPlayHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MediaPlayHelper.class) {
                if (instance == null) {
                    instance = new MediaPlayHelper(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayHelper(Context context) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();

    }

    /**
     * 播放音乐
     */
    public void start() {
        if (mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    /**
     * 暂停播放
     */
    public void pause() {
        mMediaPlayer.pause();

    }

    /**
     * 设置需要播放的音乐源
     * 该方法需要处理的几个逻辑
     * 1.音乐正在播放，需要重置音乐播放状态
     * 2.设置音乐播放路径
     * 3.准备播放
     *
     * @param path
     */
    public void setPath(String path) {
        this.mPath = path;
        //1.音乐正在播放，重置状态
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        //2.设置路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.准备播放
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mMediaPlayerHelperListener != null) {
                    mMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
    }

    public String getPath() {
        return mPath;
    }

    private OnMediaPlayerHelperListener mMediaPlayerHelperListener;

    public void setMediaPlayerHelperListener(OnMediaPlayerHelperListener mediaPlayerHelperListener) {
        mMediaPlayerHelperListener = mediaPlayerHelperListener;
    }

    public interface OnMediaPlayerHelperListener {
        void onPrepared(MediaPlayer mediaPlayer);
    }
}
