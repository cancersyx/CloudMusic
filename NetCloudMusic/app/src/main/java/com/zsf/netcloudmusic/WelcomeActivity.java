package com.zsf.netcloudmusic;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

/**
 * @author EWorld  e-mail:852333743@qq.com
 * 2019/3/18
 */
public class WelcomeActivity extends BaseActivity {
    private Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                toMain();
            }
        }, 3 * 1000);
    }


    private void toMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
