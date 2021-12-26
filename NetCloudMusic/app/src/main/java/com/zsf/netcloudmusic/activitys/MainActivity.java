package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.adapters.MusicGridAdapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private MusicGridAdapter mMusicAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        initView();
    }

    private void initView() {
        initNavBar(false, "慕课音乐", true);

        mRecyclerView = fd(R.id.rv_grid);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mMusicAdapter = new MusicGridAdapter(this);
        mRecyclerView.setAdapter(mMusicAdapter);
    }


}
