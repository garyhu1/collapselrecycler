package com.garyhu.citypickerdemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者： garyhu.
 * 时间： 2016/11/16.
 */

public class NetworkHolder implements Holder<String> {

    private SimpleDraweeView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new SimpleDraweeView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, String data) {
        imageView.setImageURI(Uri.parse(data));
    }
}
