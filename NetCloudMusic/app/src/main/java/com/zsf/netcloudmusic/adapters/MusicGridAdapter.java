package com.zsf.netcloudmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.AlbumListActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/12/26
 */
public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {
    private Context mContext;

    public MusicGridAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load("https://p.qqan.com/up/2020-8/15982538688744505.jpg")
                .into(holder.icon);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AlbumListActivity.class);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
