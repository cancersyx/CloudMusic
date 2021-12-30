package com.zsf.netcloudmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.AlbumListActivity;
import com.zsf.netcloudmusic.models.AlbumModel;
import com.zsf.netcloudmusic.views.WEqualsHImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/12/26
 */
public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {
    private static final String TAG = "MusicGridAdapter";
    private Context mContext;
    private List<AlbumModel> mDataSource;

    public MusicGridAdapter(Context context, List<AlbumModel> dataSource) {
        this.mContext = context;
        this.mDataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumModel albumModel = mDataSource.get(position);
        //"https://p.qqan.com/up/2020-8/15982538688744505.jpg"
        Log.d(TAG, ">>>>>>> poster = " + albumModel.getPoster());
        Log.d(TAG, ">>>>>>> name = " + albumModel.getName());
        Glide.with(mContext).load(albumModel.getPoster()).into(holder.icon);
        holder.playNum.setText(albumModel.getPlayNum());
        holder.name.setText(albumModel.getName());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AlbumListActivity.class);
            intent.putExtra(AlbumListActivity.ALBUM_ID, albumModel.getAlbumId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        WEqualsHImageView icon;
        TextView playNum;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            playNum = itemView.findViewById(R.id.tv_play_num);
            name = itemView.findViewById(R.id.tv_name);
        }
    }
}
