package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.utils.UserUtils;
import com.zsf.netcloudmusic.views.InputView;

import androidx.annotation.Nullable;

/**
 * Created by EWorld
 * 2021/12/23
 */
public class ChangePasswordActivity extends BaseActivity {
    private InputView mOldPassword, mNewPassword, mPasswordConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        initNavBar(true, "修改密码", false);
        mOldPassword = fd(R.id.input_old_password);
        mNewPassword = fd(R.id.input_password);
        mPasswordConfirm = fd(R.id.input_password_confirm);

    }

    public void onChangePasswordBtn(View view) {
        String oldPassword = mOldPassword.getInputStr();
        String newPassword = mNewPassword.getInputStr();
        String confirmPassword = mPasswordConfirm.getInputStr();

        boolean result = UserUtils.changePassword(this, oldPassword, newPassword, confirmPassword);
        if (!result) {
            return;
        }
        UserUtils.logout(this);
    }
}
