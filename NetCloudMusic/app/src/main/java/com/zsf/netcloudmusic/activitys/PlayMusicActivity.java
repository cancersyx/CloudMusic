package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.helps.RealmHelper;
import com.zsf.netcloudmusic.models.MusicModel;
import com.zsf.netcloudmusic.views.PlayMusicView;

import androidx.annotation.Nullable;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by EWorld
 * 2021/12/27
 */
public class PlayMusicActivity extends BaseActivity {
    public static final String MUSIC_ID = "musicId";
    private ImageView mBgView;
    private PlayMusicView mPlayMusicView;
    private TextView mMusicName;
    private TextView mAuthor;

    private String musicId;
    private MusicModel mMusicModel;
    private RealmHelper mRealmHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();

    }

    private void initData() {
        musicId = getIntent().getStringExtra(MUSIC_ID);
        mRealmHelper = new RealmHelper();
        mMusicModel = mRealmHelper.getMusic(musicId);
    }

    private void initView() {
        mBgView = fd(R.id.iv_bg);
        mMusicName = fd(R.id.tv_name);
        mAuthor = fd(R.id.tv_author);
        //glide-transformations配合Glide来实现高斯模糊效果
        /*Glide.with(this).load("https://up.enterdesk.com/edpic/bf/e9/0e/bfe90e6e7e7ffba795de257cb81f1795.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(mBgView);*/
        Glide.with(this).load(mMusicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(mBgView);

        mMusicName.setText(mMusicModel.getName());
        mAuthor.setText(mMusicModel.getAuthor());

        mPlayMusicView = fd(R.id.play_music_view);
        /*mPlayMusicView.setMusicIcon("https://up.enterdesk.com/edpic/bf/e9/0e/bfe90e6e7e7ffba795de257cb81f1795.jpg");
        mPlayMusicView.playMusic("https://freepd.com/music/Gotta%20Keep%20On%20Movin.mp3");*/
        //mPlayMusicView.setMusicIcon(mMusicModel.getPoster());
        mPlayMusicView.setMusic(mMusicModel);
        mPlayMusicView.playMusic();
    }

    public void onBackClick(View view) {
        onBackPressed();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
        //解除绑定服务
        mPlayMusicView.destroyBind();
    }
}
