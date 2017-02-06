package com.garyhu.citypickerdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.base.BaseActivity;
import com.garyhu.citypickerdemo.widget.drager.DragPhotoActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者： garyhu.
 * 时间： 2017/2/6.
 */

public class DragActivity extends BaseActivity {

    @BindView(R.id.imageView1)
    ImageView img1;
    @BindView(R.id.imageView2)
    ImageView img2;
    @BindView(R.id.imageView3)
    ImageView img3;
    @BindView(R.id.imageView4)
    ImageView img4;
    @BindView(R.id.imageView5)
    ImageView img5;

    private ArrayList<Integer> imgResIds = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_drag;
    }

    @Override
    protected void init() {
        addImgResId();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhotoActivity(DragActivity.this,img1,0);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhotoActivity(DragActivity.this,img2,1);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhotoActivity(DragActivity.this,img3,2);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhotoActivity(DragActivity.this,img4,3);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhotoActivity(DragActivity.this,img5,4);
            }
        });
    }

    /** 添加图片资源*/
    public void addImgResId(){
        imgResIds.add(R.drawable.img1);
        imgResIds.add(R.drawable.img2);
        imgResIds.add(R.drawable.img3);
        imgResIds.add(R.drawable.img4);
        imgResIds.add(R.drawable.img5);
    }

    @Override
    protected void setStatus() {
        //此方法用于设置铺满整个屏幕
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //此方法设置状态栏为透明
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
    }

    public  void startPhotoActivity(Context context, ImageView imageView,int num) {
        Intent intent = new Intent(context, DragPhotoActivity.class);
        int location[] = new int[2];

        imageView.getLocationOnScreen(location);
        intent.putExtra("num",num);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());
        intent.putIntegerArrayListExtra("imgs",imgResIds);

        context.startActivity(intent);
        overridePendingTransition(0,0);
    }
}
