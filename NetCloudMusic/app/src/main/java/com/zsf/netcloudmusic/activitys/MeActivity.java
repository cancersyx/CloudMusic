package com.zsf.netcloudmusic.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.BaseActivity;
import com.zsf.netcloudmusic.helps.UserHelper;
import com.zsf.netcloudmusic.utils.UserUtils;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/23
 */
public class MeActivity extends BaseActivity {
    private TextView mUserTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        iniitView();
    }

    private void iniitView() {
        initNavBar(true, "个人中心", false);
        mUserTv = fd(R.id.tv_user);
        mUserTv.setText("用户名：" + UserHelper.getInstance().getPhone());
    }


    /**
     * 退出登录
     *
     * @param view
     */
    public void onLogoutClick(View view) {
        UserUtils.logout(this);

    }

    /**
     * 修改密码点击事件
     *
     * @param view
     */
    public void onChangeClick(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }
}
