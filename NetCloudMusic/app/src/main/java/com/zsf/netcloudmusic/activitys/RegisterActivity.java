package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;

import com.zsf.netcloudmusic.R;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/25
 */
public class RegisterActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);

    }

    public void onClickRegisterBtn(View view) {

    }
}
