package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.adapters.MusicGridAdapter;
import com.zsf.netcloudmusic.adapters.MusicListAdapter;
import com.zsf.netcloudmusic.views.GridSpaceItemDecoration;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView, mHotMusicsRv;
    private MusicGridAdapter mMusicGridAdapter;
    private MusicListAdapter mMusicListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        initView();
    }

    private void initView() {
        initNavBar(false, "慕课音乐", true);

        mRecyclerView = fd(R.id.rv_grid);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setNestedScrollingEnabled(false);//禁止RecyclerView滑动
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRecyclerView));
        mMusicGridAdapter = new MusicGridAdapter(this);
        mRecyclerView.setAdapter(mMusicGridAdapter);

        /**
         * 1.假设已经知道列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上;
         * 2.不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */
        mHotMusicsRv = fd(R.id.rv_list);
        mHotMusicsRv.setNestedScrollingEnabled(false);//禁止RecyclerView滑动
        mHotMusicsRv.setLayoutManager(new LinearLayoutManager(this));
        mMusicListAdapter = new MusicListAdapter(this, mHotMusicsRv);
        mHotMusicsRv.setAdapter(mMusicListAdapter);
    }


}
