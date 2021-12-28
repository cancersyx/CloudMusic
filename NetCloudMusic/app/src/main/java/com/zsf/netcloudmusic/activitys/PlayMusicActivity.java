package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.views.PlayMusicView;

import androidx.annotation.Nullable;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by EWorld
 * 2021/12/27
 */
public class PlayMusicActivity extends BaseActivity {
    private ImageView mBgView;
    private PlayMusicView mPlayMusicView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }

    private void initView() {
        mBgView = fd(R.id.iv_bg);
        //glide-transformations配合Glide来实现高斯模糊效果
        Glide.with(this).load("https://up.enterdesk.com/edpic/bf/e9/0e/bfe90e6e7e7ffba795de257cb81f1795.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(mBgView);

        mPlayMusicView = fd(R.id.play_music_view);
        mPlayMusicView.setMusicIcon("https://up.enterdesk.com/edpic/bf/e9/0e/bfe90e6e7e7ffba795de257cb81f1795.jpg");
        mPlayMusicView.playMusic("https://freepd.com/music/Gotta%20Keep%20On%20Movin.mp3");
    }

    public void onBackClick(View view) {
        onBackPressed();

    }
}
