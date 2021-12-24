package com.zsf.netcloudmusic.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zsf.netcloudmusic.R;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/23
 * NavigationBar
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        initNavBar(false, "登录", false);
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);


    }
}
