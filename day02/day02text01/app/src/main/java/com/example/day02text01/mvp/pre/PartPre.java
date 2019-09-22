package com.example.day02text01.mvp.pre;

import android.util.Log;

import com.example.day02text01.PartCallBack;
import com.example.day02text01.beans.PartBean;
import com.example.day02text01.mvp.model.PartModel;
import com.example.day02text01.mvp.view.PartView;

import java.util.List;

public class PartPre implements PartCallBack {

    private PartView mPartView;
    private final PartModel mPartModel;

    public PartPre(PartView partView) {
        mPartView = partView;
        mPartModel = new PartModel();
    }

    public void getData(int id){
        mPartModel.getData(this,id);
    }

    @Override
    public void onSuccess( List<PartBean.BodyBean.ResultBean> list) {
        mPartView.onSuccess(list);
    }

    @Override
    public void onFile(String msg) {
        mPartView.onFile(msg);
    }
}
