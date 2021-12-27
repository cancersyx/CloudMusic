package com.zsf.netcloudmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.AlbumListActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/12/27
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalculationRvHeight;

    public MusicListAdapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.mRv = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music, parent, false);
        ViewHolder holder = new ViewHolder(mItemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();
        Glide.with(mContext).load("https://img9.doubanio.com/icon/up180220961-13.jpg")
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 1.获取ItemView高度
     * 2.itemView的数量
     * 3.使用itemViewHeight * itemViewNum = RecyclerView的高度
     */
    public void setRecyclerViewHeight() {
        if (isCalculationRvHeight || mRv == null) return;
        isCalculationRvHeight = true;
        //获取ItemView的高度
        RecyclerView.LayoutParams itemViewRp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        int itemCount = getItemCount();
        int recyclerViewHeight = itemViewRp.height * itemCount;
        //设置RecyclerView高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
