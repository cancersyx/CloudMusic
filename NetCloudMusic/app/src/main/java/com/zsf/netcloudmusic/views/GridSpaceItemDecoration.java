package com.zsf.netcloudmusic.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by EWorld
 * 2021/12/27
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public GridSpaceItemDecoration(int space,RecyclerView recyclerView) {
        this.mSpace = space;
        getRecyclerViewOffsets(recyclerView);
    }


    /**
     * 每个ItemView设置偏移量的时候，该方法都会被调用一次！！！
     * 设置每一个Item 的偏移量;
     *
     * @param outRect Item矩形边界
     * @param view    ItemView
     * @param parent  RecyclerView
     * @param state   RecyclerView的状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;

        //判断item是否是每一行第一个Item
       /* if (parent.getChildLayoutPosition(view) % 3 == 0) {
            outRect.left = 0;
        }*/


        //View Margin
        //margin为正，则View会距离边界产生一个距离；margin为负，则View会超出边界产生一个距离
        /*LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);*/
    }

    /**
     * 从getItemOffsets()方法抽离出来，避免多次调用
     * @param parent
     */
    private void getRecyclerViewOffsets(RecyclerView parent){
        //View Margin
        //margin为正，则View会距离边界产生一个距离；margin为负，则View会超出边界产生一个距离
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
