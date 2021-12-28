package com.zsf.netcloudmusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.helps.MediaPlayHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/28
 */
public class PlayMusicView extends FrameLayout {
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

    private MediaPlayHelper mMediaPlayHelper;

    private String mPath;//音乐路径

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

        mMediaPlayHelper = MediaPlayHelper.getInstance(mContext);

    }

    private void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
        }
    }

    /**
     * 开始音乐
     */
    public void playMusic(String path) {
        this.mPath = path;
        isPlaying = true;
        mIvPlay.setVisibility(GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mNeedleIv.startAnimation(mPlayNeedleAnim);

        if (mMediaPlayHelper.getPath() != null && mMediaPlayHelper.getPath().equals(path)) {
            mMediaPlayHelper.start();
        } else {
            mMediaPlayHelper.setPath(path);
            mMediaPlayHelper.setMediaPlayerHelperListener(new MediaPlayHelper.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mMediaPlayHelper.start();
                }
            });
        }

    }

    /**
     * 停止音乐
     */
    public void stopMusic() {
        isPlaying = false;
        mIvPlay.setVisibility(VISIBLE);
        mFlPlayMusic.clearAnimation();
        mNeedleIv.startAnimation(mStopNeedleAnim);
        ////
        mMediaPlayHelper.pause();
    }


    /**
     * 设置光盘中显示的音乐封面图片
     *
     * @param icon 图片地址
     */
    public void setMusicIcon(String icon) {
        Glide.with(mContext).load(icon).into(mIvIcon);
    }
}
