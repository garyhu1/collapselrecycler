package com.garyhu.citypickerdemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.widget.BossZoomHelper;
import com.garyhu.citypickerdemo.widget.CircleImageView;
import com.garyhu.citypickerdemo.widget.gallery.ViewPagerGallery;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： garyhu.
 * 时间： 2016/12/17.
 */

public class GalleryActivity extends AppCompatActivity {

    private ViewPagerGallery gallery;
    private RelativeLayout activitymain;
    private String url = "http://uploadfile.bizhizu.cn/2015/0731/20150731051718540.jpg";
    private SimpleDraweeView sdv;
    private CircleImageView img;
    private BossZoomHelper helper;

    private boolean isShow = false;//图片是否点击放大了

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
        this.gallery = (ViewPagerGallery) findViewById(R.id.gallery);
//        sdv = (SimpleDraweeView) findViewById(R.id.sdv);
        img = (CircleImageView) findViewById(R.id.sdv);
//        loadLocalImg();
//        loadImg();
        setTranslucentStatus(true);
        setHeadView();
        loadNetImg();
        setListener();
    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * 加载头像
     */
    public void loadImg(){
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        sdv.setHierarchy(hierarchy);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(url))
                .build();
        sdv.setController(controller);
    }

    public void setHeadView(){
        Glide.with(this).load(url).into(img);
    }

    /**
     * 点击头像放大图片
     */
    public void setListener(){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new BossZoomHelper(GalleryActivity.this,img,400);
                helper.showAnim();
                isShow = true;
            }
        });
    }

    /**
     * 加载本地图片
     */
    public void loadLocalImg(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 9; i++){
            int id = getResources().getIdentifier("img" + i, "drawable", getPackageName());
            list.add(id);
        }
        gallery.setImgResources(list);
    }

    /**
     * 加载网络图片
     */
    public void loadNetImg(){
        List<String> list = new ArrayList<>();
        list.add("http://tupian.enterdesk.com/2015/lxy/01/019/1/9.jpg");
        list.add("http://tupian.aladd.net/2015/8/908.jpg");
        list.add("http://tupian.enterdesk.com/2015/lxy/01/019/1/4.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/f301921f4134970aad0f6af395cad1c8a5865dc4.jpg");
        list.add("http://tupian.enterdesk.com/2015/lxy/01/019/1/6.jpg");
        list.add("http://easyread.ph.126.net/f2DYWftpxEdjjbLzQD6cgw==/7916877345151005395.jpg");
        list.add("http://rescdn.qqmail.com/dyimg/20140202/7BC1635CBA3F.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/a628c051f3deb48ffb5dc602f01f3a292cf5780c.jpg");
        list.add("http://easyread.ph.126.net/FdXx6SX4uct_FBTnq6s1Iw==/7916658542337077456.jpg");
        gallery.setImgUrls(list);
    }

    public void onClick(View view) {
        startActivity(new Intent(this,PhotoViewActivity.class));
    }

    @Override
    public void onBackPressed() {
        if(isShow){
            helper.hintAnim();
            isShow = false;
        }else {
            super.onBackPressed();
        }
    }
}
