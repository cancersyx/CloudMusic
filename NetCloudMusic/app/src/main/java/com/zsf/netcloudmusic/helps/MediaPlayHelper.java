package com.zsf.netcloudmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by EWorld
 * 2021/12/28
 * 音乐播放3种方式
 * 1.直接在Activity创建播放音乐，音乐与Activity绑定
 * 2.通过全局单例类与Application绑定，Application在音乐在
 * 3.Service进行播放
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
        /**
         * 1.音乐处于播放状态，那么就重置音乐播放状态
         * 2.如果音乐没有在播放状态（如暂停），则不需要重置播放状态
         */
        //1.音乐正在播放或切换了音乐，重置状态
        if (mMediaPlayer.isPlaying() || !path.equals(mPath)) {
            mMediaPlayer.reset();
        }
        this.mPath = path;
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

        //监听音乐播放完成
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mMediaPlayerHelperListener != null){
                    mMediaPlayerHelperListener.onCompletion(mp);
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

        void onCompletion(MediaPlayer mediaPlayer);
    }
}
