package com.garyhu.citypickerdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public abstract class BaseActivity extends SupportActivity {

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
        init();
    }

    protected abstract int getLayoutRes();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        if (unbinder!=null)
            unbinder.unbind();
        super.onDestroy();
    }
}
