package com.example.day02text03.mvp.pre;

import com.example.day02text03.CallBack;
import com.example.day02text03.beas.FuliBean;
import com.example.day02text03.mvp.model.FuliModel;
import com.example.day02text03.mvp.view.FuliView;

import java.util.List;

public class FuliPre implements CallBack {

    private FuliView mFuliView;
    private final FuliModel mFuliModel;

    public FuliPre(FuliView fuliView) {
        mFuliView = fuliView;
        mFuliModel = new FuliModel();
    }

    public void getData(){
        mFuliModel.getData(this);
    }

    @Override
    public void onSuccess(List<FuliBean.ResultsBean> list) {
        mFuliView.onSuccess(list);
    }

    @Override
    public void onFile(String msg) {
        mFuliView.onFile(msg);
    }
}
