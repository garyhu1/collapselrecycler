package com.garyhu.citypickerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.garyhu.citypickerdemo.R;

/**
 * 作者： garyhu.
 * 时间： 2016/12/14.
 * 自定义的下拉刷新的HeaderView
 */

public class RefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
    }
}
