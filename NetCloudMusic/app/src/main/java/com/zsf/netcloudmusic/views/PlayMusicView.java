package com.zsf.netcloudmusic.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.models.MusicModel;
import com.zsf.netcloudmusic.services.MusicService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/28
 */
public class PlayMusicView extends FrameLayout {
    private Intent mServiceIntent;
    private Context mContext;
    private View mView;
    private ImageView mIvIcon;
    private FrameLayout mFlPlayMusic;
    private ImageView mNeedleIv;
    private ImageView mIvPlay;

    private Animation mPlayMusicAnim;
    private Animation mPlayNeedleAnim;
    private Animation mStopNeedleAnim;

    private boolean isPlaying;
    private boolean isBindService;

    private MusicService.MusicBind mMusicBind;
    private MusicModel mMusicModel;

    public PlayMusicView(@NonNull Context context) {
        this(context, null);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mNeedleIv = mView.findViewById(R.id.iv_needle);
        mIvPlay = mView.findViewById(R.id.iv_play);

        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });

        /**
         * 一、定义所需要的动画
         *      1.光盘转动的动画
         *      2.指针指向光盘的动画
         *      3.指针离开光盘的动画
         * 二、startAnimation
         *
         */
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);

        addView(mView);


    }

    private void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic();
        }
    }

    /**
     * 开始音乐
     */
    public void playMusic() {
        isPlaying = true;
        mIvPlay.setVisibility(GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mNeedleIv.startAnimation(mPlayNeedleAnim);

        //开启服务
        startMusicService();
    }

    /**
     * 停止音乐
     */
    public void stopMusic() {
        isPlaying = false;
        mIvPlay.setVisibility(VISIBLE);
        mFlPlayMusic.clearAnimation();
        mNeedleIv.startAnimation(mStopNeedleAnim);

        if (mMusicBind != null) {
            //停止服务
            mMusicBind.stopMusic();
        }

    }


    /**
     * 设置光盘中显示的音乐封面图片
     */
    public void setMusicIcon() {
        Glide.with(mContext).load(mMusicModel.getPoster()).into(mIvIcon);
    }

    /**
     * @param musicModel
     */
    public void setMusic(MusicModel musicModel) {
        this.mMusicModel = musicModel;
        setMusicIcon();
    }

    /**
     * 启动音乐服务
     */
    public void startMusicService() {
        //启动Service
        if (mServiceIntent == null) {
            mServiceIntent = new Intent(mContext, MusicService.class);
            mContext.startService(mServiceIntent);
        } else {
            mMusicBind.playMusic();
        }
        //绑定Service
        //1.service未绑定，先去绑定
        if (!isBindService) {
            isBindService = true;
            mContext.bindService(mServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    /**
     * 解除绑定
     */
    public void destroyBind() {
        if (isBindService) {
            isBindService = false;
            mContext.unbindService(mServiceConnection);
        }
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicBind = (MusicService.MusicBind) service;
            mMusicBind.setMusic(mMusicModel);
            mMusicBind.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
