package com.zsf.netcloudmusic;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
