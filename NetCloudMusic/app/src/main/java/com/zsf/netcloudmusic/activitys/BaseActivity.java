package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsf.netcloudmusic.R;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author EWorld  e-mail:852333743@qq.com
 * 2019/3/18
 */
public class BaseActivity extends AppCompatActivity {
    private ImageView mBackIv, mMeIv;
    private TextView mTitleTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 接受一个资源Id
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }

    /**
     * 初始化NavigationBar
     *
     * @param isShowBack
     * @param title
     * @param isShowMe
     */
    protected void initNavBar(boolean isShowBack, String title, boolean isShowMe) {
        mBackIv = fd(R.id.iv_back);
        mTitleTv = fd(R.id.tv_title);
        mMeIv = fd(R.id.iv_me);

        mBackIv.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mMeIv.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        mTitleTv.setText(title);

        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
