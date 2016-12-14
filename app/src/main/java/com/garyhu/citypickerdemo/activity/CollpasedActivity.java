package com.garyhu.citypickerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.garyhu.citypickerdemo.R;

/**
 * Created by garyhu
 * on 2016/12/14.
 * 折叠布局
 */

public class CollpasedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collaps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);

        collaps = ((CollapsingToolbarLayout) findViewById(R.id.collapse_layout));
//        collaps.setTitle("我下面给你吃");

        toolbar = ((Toolbar) findViewById(R.id.tool_bar));
        toolbar.setTitle("饿了么");
    }
}
