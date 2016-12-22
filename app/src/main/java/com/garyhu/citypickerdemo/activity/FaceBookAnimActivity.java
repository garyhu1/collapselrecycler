package com.garyhu.citypickerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.garyhu.citypickerdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class FaceBookAnimActivity extends AppCompatActivity {

    private ImageView mView;
    private RelativeLayout viewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        mView = (ImageView) findViewById(R.id.icon);
        viewGroup = (RelativeLayout) findViewById(R.id.re_layout);
        SpringSystem springSystem = SpringSystem.create();
        Spring spring = springSystem.createSpring();
        spring.addListener(new SimpleSpringListener(){
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                float va = (float) spring.getCurrentValue();
                float scale = 1f-0.5f*va;
                mView.setScaleX(scale);
                mView.setScaleY(scale);
            }
        });
        //set the spring in motion; moving from 0 to 1
        spring.setEndValue(1);
        /*
         好像弹簧效果不明显，Rebound默认的拉力和摩擦力参数分别是40和7，
         我们看下Rebound里面有个defaultConfig
         public static SpringConfig defaultConfig = SpringConfig.fromOrigamiTensionAndFriction(40, 7);
         为了让弹簧效果更明显，我们修改下SpringConfig的值，代码如下：
         我们将拉力值改成100，摩擦力值改成1，
         */
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(100,1));

        //设置多个View联动效果
        setMoreViewAnim();
    }

    public void setMoreViewAnim(){
        /*
        其中带参数的第一个参数表示起主导作用spring的拉力系数，
        第二个参数表示起主导作用Spring的摩擦力系数，
        第三个和第四个表示附属的拉力和摩擦力系数
         */
        SpringChain springChain = SpringChain.create(40,6,50,7);

        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = viewGroup.getChildAt(i);

            springChain.addSpring(new SimpleSpringListener() {
                @Override
                public void onSpringUpdate(Spring spring) {
                    view.setTranslationY((float) spring.getCurrentValue());
                }
            });
        }

        List<Spring> springs = springChain.getAllSprings();
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).setCurrentValue(400);
        }

        springChain.setControlSpringIndex(4).getControlSpring().setEndValue(0);

    }

}
