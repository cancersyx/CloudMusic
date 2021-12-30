package com.zsf.netcloudmusic.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.WelcomeActivity;
import com.zsf.netcloudmusic.helps.MediaPlayHelper;
import com.zsf.netcloudmusic.models.MusicModel;

import androidx.annotation.RequiresApi;

/**
 * 一、通过Service连接PlayMusicView和MediaPlayHelper
 * 二、PlayMusicView控制--MusicService服务
 * 1.播放音乐、暂停音乐
 * 2.启动service、绑定service、解绑service
 * <p>
 * 三、MediaplayHelper -- service
 * 1.播放音乐、暂停音乐
 * 2.监听音乐播放完成、停止Service
 * <p>
 * ********************************************************
 * 系统默认不允许不可见的后台服务播放音乐
 */
public class MusicService extends Service {
    //NOTIFICATION_ID不可为0
    public static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "333";

    private MediaPlayHelper mMediaPlayHelper;
    private MusicModel mMusicModel;

    public MusicService() {
    }

    public class MusicBind extends Binder {

        /**
         * 设置音乐
         *
         * @param musicModel
         */
        public void setMusic(MusicModel musicModel) {
            mMusicModel = musicModel;
            startForegroundService();
        }

        /**
         *
         */
        public void playMusic() {
            if (mMediaPlayHelper.getPath() != null && mMediaPlayHelper.getPath().equals(mMusicModel.getPath())) {
                mMediaPlayHelper.start();
            } else {
                mMediaPlayHelper.setPath(mMusicModel.getPath());
                mMediaPlayHelper.setMediaPlayerHelperListener(new MediaPlayHelper.OnMediaPlayerHelperListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mMediaPlayHelper.start();
                    }

                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopSelf();//停止Service
                    }
                });
            }
        }

        /**
         * 暂停播放
         */
        public void stopMusic() {
            //
            mMediaPlayHelper.pause();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayHelper = MediaPlayHelper.getInstance(this);

    }


    /**
     * 设置服务前台可见
     */
    private void startForegroundService() {
        //通知栏点击跳转的PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, WelcomeActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);

        //创建Notification
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = createNotificationChannel();
            notification = new Notification.Builder(this, channel.getId())
                    .setContentTitle(mMusicModel.getName())
                    .setContentText(mMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        } else {
            notification = new Notification.Builder(this)
                    .setContentTitle(mMusicModel.getName())
                    .setContentText(mMusicModel.getAuthor())
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(pendingIntent)
                    .build();
        }
        //设置Notification在前台展示（Android 9.0记得添加权限 FOREGROUND_SERVICE）
        startForeground(NOTIFICATION_ID, notification);
    }

    /**
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel() {
        String channelId = "imooc";
        String channelName = "imoocMusicService";
        String description = "moocMusic";
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        channel.setShowBadge(false);
        return channel;
    }
}