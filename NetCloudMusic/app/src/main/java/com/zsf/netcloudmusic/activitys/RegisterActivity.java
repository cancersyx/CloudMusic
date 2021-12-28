package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.utils.UserUtils;
import com.zsf.netcloudmusic.views.InputView;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/25
 */
public class RegisterActivity extends BaseActivity {

    private InputView mPhoneInput;
    private InputView mPasswordInput;
    private InputView mPasswordConfirmInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(true, "注册", false);
        mPhoneInput = fd(R.id.input_phone);
        mPasswordInput = fd(R.id.input_password);
        mPasswordConfirmInput = fd(R.id.input_password_confirm);
    }

    /**
     * 注册按钮点击事件
     * 一、用户输入合法性验证
     * 1.手机号是否合法
     * 2.用户密码验证
     *
     * @param view
     */
    public void onClickRegisterBtn(View view) {
        String phone = mPhoneInput.getInputStr();
        String password = mPasswordInput.getInputStr();
        String passwordConfirm = mPasswordConfirmInput.getInputStr();

        boolean result = UserUtils.registerUser(this, phone, password, passwordConfirm);
        if (!result) return;
        //后退页面
        onBackPressed();
    }
}
