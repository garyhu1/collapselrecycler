package com.garyhu.citypickerdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.garyhu.citypickerdemo.adapter.NetworkHolder;
import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.util.ScreenUtil;
import com.garyhu.citypickerdemo.activity.SecondActivity;
import com.garyhu.citypickerdemo.widget.CusConvenientBanner;
import com.garyhu.citypickerdemo.widget.CusPtrClassicFrameLayout;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * 作者： garyhu.
 * 时间： 2016/12/8.
 */

public class MyFragment extends Fragment {

    private TextView tv;
    private Button btn;
    private CusConvenientBanner banner;
    private CusPtrClassicFrameLayout ptr;
    private List<String> imageUrls;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,null);
        tv = (TextView) view.findViewById(R.id.tv);
        btn = (Button) view.findViewById(R.id.new_activity);
        ptr = (CusPtrClassicFrameLayout) view.findViewById(R.id.ptr);
        banner = (CusConvenientBanner) view.findViewById(R.id.banner);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),SecondActivity.class);
                startActivityForResult(in,1);
            }
        });

        initData();
        setBanner();
        setPtr();

    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startTurning(3000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == 2){
//                tv.setText(data.getStringExtra("data"));
            }
        }
    }

    /**
     * 初始化图片资源
     */
    public void initData(){
        imageUrls = new ArrayList<>();
        imageUrls.add("http://img2.3lian.com/2014/f3/53/d/110.jpg");
        imageUrls.add("http://img5.imgtn.bdimg.com/it/u=435370533,4135187820&fm=11&gp=0.jpg");
        imageUrls.add("http://tupian.enterdesk.com/2012/0617/gha/2/132072927353SJKg.jpg");
        imageUrls.add("http://img1.gtimg.com/sports/pics/hv1/192/62/1430/93001752.jpg");
    }

    /**
     * 设置Banner
     */
    public void setBanner(){
        banner.setPages(new CBViewHolderCreator<NetworkHolder>() {
            @Override
            public NetworkHolder createHolder() {
                return new NetworkHolder();
            }
        },imageUrls);
        banner.getViewPager().setPageTransformer(true,new StackTransformer());
    }

    /**
     * 设置刷新
     */
    public void setPtr(){
        final StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.setPadding(0, ScreenUtil.dip2px(getActivity(),15), 0, 0);
        header.initWithString("GARYHU");
        header.setTextColor(0xff098111);
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
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
                }, 2000);
            }
        });
    }
}
