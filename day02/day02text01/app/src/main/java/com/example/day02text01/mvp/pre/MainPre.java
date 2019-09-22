package com.example.day02text01.mvp.pre;

import com.example.day02text01.CallBack;
import com.example.day02text01.beans.Bean;
import com.example.day02text01.mvp.model.MainModel;
import com.example.day02text01.mvp.view.MainView;

import java.util.List;

public class MainPre implements CallBack {

    private MainView mMainView;
    private final MainModel mMainModel;

    public MainPre(MainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel();
    }

    public void getData(){
        mMainModel.getData(this);
    }

    @Override
    public void onSuccess(List<Bean.BodyBean.ResultBean> list) {
        mMainView.onSuccess(list);
    }

    @Override
    public void onFile(String msg) {
        mMainView.onFile(msg);
    }
}
