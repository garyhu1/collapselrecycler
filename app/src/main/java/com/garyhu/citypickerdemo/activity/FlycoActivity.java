package com.garyhu.citypickerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.base.BaseActivity;
import com.garyhu.citypickerdemo.fragment.ForthFragment;
import com.garyhu.citypickerdemo.fragment.MainFragment;
import com.garyhu.citypickerdemo.fragment.SecondFragment;
import com.garyhu.citypickerdemo.fragment.ThirdFragment;
import com.garyhu.citypickerdemo.model.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public class FlycoActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tl)
    CommonTabLayout mTabLayout;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"首页", "快速报价", "我的装修", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.buttona2,R.drawable.buttonb2,
            R.drawable.buttone1,R.drawable.buttond2};
    private int[] mIconSelectIds = {
            R.drawable.buttona1,R.drawable.buttonb1,
            R.drawable.buttone2,R.drawable.buttond1};
    //tab的标题、选中图标、未选中图标
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        //给tab设置数据和关联的fragment
        mTabLayout.setTabData(mTabEntities, this, R.id.fl_change, mFragments);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_flyco;
    }

    private void initData() {
        mFragments.add(new MainFragment());
        mFragments.add(new SecondFragment());
        mFragments.add(new ThirdFragment());
        mFragments.add(new ForthFragment());
        //设置tab的标题、选中图标、未选中图标
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
    }

}
