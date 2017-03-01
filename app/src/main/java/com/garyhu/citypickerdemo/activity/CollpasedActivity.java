package com.garyhu.citypickerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.inter.AppBarStateChangeListener;

/**
 * Created by garyhu
 * on 2016/12/14.
 * 折叠布局
 */

public class CollpasedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collaps;
    private TabLayout tab;
    private AppBarLayout aBL;
    private LinearLayout  ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);

        collaps = ((CollapsingToolbarLayout) findViewById(R.id.collapse_layout));
        aBL = (AppBarLayout) findViewById(R.id.abl);
        toolbar = ((Toolbar) findViewById(R.id.tool_bar));
        ll = (LinearLayout) findViewById(R.id.ll);
        aBL.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    //展开状态
                    ll.setVisibility(View.GONE);
                }else if(state == State.COLLAPSED){
                    //折叠状态
                    ll.setVisibility(View.VISIBLE);
                }else {
                    ll.setVisibility(View.VISIBLE);
                    //中间状态
                }
            }
        });

//        toolbar.setTitle("饿了么");
    }
}
