package com.zsf.netcloudmusic.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.utils.UserUtils;
import com.zsf.netcloudmusic.views.InputView;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/23
 * NavigationBar
 */
public class LoginActivity extends BaseActivity {
    private InputView mInputPhone, mInputPassword;

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
        mInputPassword = fd(R.id.input_password);
        mInputPhone = fd(R.id.input_phone);
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);


    }

    public void onClickRegisterBtn(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void onClickLoginBtn(View view) {
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        //验证用户输入是否合法
        if (!UserUtils.validateLogin(this, phone, password)) {
            return;
        }
        //跳转主页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
