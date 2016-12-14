package com.garyhu.citypickerdemo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者： garyhu.
 * 时间： 2016/12/9.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
