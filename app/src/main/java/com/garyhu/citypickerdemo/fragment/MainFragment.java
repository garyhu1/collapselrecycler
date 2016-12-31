package com.garyhu.citypickerdemo.fragment;

import android.view.View;
import android.widget.TextView;

import com.garyhu.citypickerdemo.R;
import com.garyhu.citypickerdemo.base.BaseFragment;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 作者： garyhu.
 * 时间： 2016/12/22.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.dialog)
    TextView showDialog;

    private SweetAlertDialog dialog;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

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
