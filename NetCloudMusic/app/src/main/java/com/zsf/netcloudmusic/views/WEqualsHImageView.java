package com.zsf.netcloudmusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/26
 */
public class WEqualsHImageView extends androidx.appcompat.widget.AppCompatImageView {
    public WEqualsHImageView(@NonNull Context context) {
        super(context);
    }

    public WEqualsHImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        //获取宽度
        //int width = MeasureSpec.getSize(widthMeasureSpec);

    }
}
