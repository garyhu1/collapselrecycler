package com.garyhu.citypickerdemo.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.base.BaseFragment;
import com.garyhu.citypickerdemo.widget.myrecyclerview.MyRecyclerView;
import com.garyhu.citypickerdemo.widget.myrecyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public class MainFragment extends BaseFragment implements MyRecyclerView.onGetListener{

    @BindView(R.id.dialog)
    TextView showDialog;
    @BindView(R.id.my_recycler)
    MyRecyclerView recyclerView;

    private SweetAlertDialog dialog;
    private List<Integer> lists = new ArrayList<>();
    private RecyclerAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        initList();
        initviews();
    }

    private void initviews() {
        adapter = new RecyclerAdapter(getActivity(), lists);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setListener(this);

    }

    private void initList() {
        for (int i = 1; i < 20; i++) {
            lists.add(i);
        }
    }


    @Override
    public void getPosition(int position) {

        Toast.makeText(getActivity(), "现在是第" + String.valueOf(position + 1) + "项", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setListener() {
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("密码修改成功")
                .setContentText("请您记住密码!")
                .setConfirmText("确认")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        if(dialog!=null&&dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                })
                .show();
    }
}
