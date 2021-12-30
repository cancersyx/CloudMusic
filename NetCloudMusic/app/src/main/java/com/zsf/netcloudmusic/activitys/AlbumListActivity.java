package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.adapters.MusicListAdapter;
import com.zsf.netcloudmusic.helps.RealmHelper;
import com.zsf.netcloudmusic.models.AlbumModel;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/12/27
 */
public class AlbumListActivity extends BaseActivity {
    public static final String ALBUM_ID = "albumId";
    private RecyclerView mRvList;
    private MusicListAdapter mMusicListAdapter;
    private String mAlbumId;
    private RealmHelper mRealmHelper;
    private AlbumModel mAlbumModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initData();
        initView();
    }

    private void initData() {
        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
        mRealmHelper = new RealmHelper();
        mAlbumModel = mRealmHelper.getAlbum(mAlbumId);
    }

    private void initView() {
        initNavBar(true, "专辑列表", false);

        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMusicListAdapter = new MusicListAdapter(this, null,mAlbumModel.getList());
        mRvList.setAdapter(mMusicListAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}
