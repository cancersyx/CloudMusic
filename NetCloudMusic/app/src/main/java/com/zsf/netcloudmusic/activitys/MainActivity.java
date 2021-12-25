package com.zsf.netcloudmusic.activitys;

import android.os.Bundle;
import android.view.View;

import com.zsf.netcloudmusic.R;
import com.zsf.netcloudmusic.activitys.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
    }

    public void onOpenLogin(View view) {
        LoginActivity.startActivity(this);
    }
}
