package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;

import com.zsf.netcloudmusic.R;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/23
 */
public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);
    }
}
