package com.garyhu.citypickerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.widget.CusPtrFrameLayout;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 作者： garyhu.
 * 时间： 2016/12/14.
 * 自定义的下拉刷新的HeaderView
 */

public class RefreshActivity extends AppCompatActivity {

    private CusPtrFrameLayout ptr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ptr = (CusPtrFrameLayout) findViewById(R.id.ptr);
        refresh();
    }

    public void refresh(){
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 3000);
            }
        });
    }
}
