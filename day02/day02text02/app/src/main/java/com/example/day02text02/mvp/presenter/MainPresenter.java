package com.example.day02text02.mvp.presenter;

import com.example.day02text02.BaseApp;
import com.example.day02text02.CallBack;
import com.example.day02text02.beans.Bean;
import com.example.day02text02.beans.UserBean;
import com.example.day02text02.mvp.model.MainModel;
import com.example.day02text02.mvp.view.MainView;

import java.util.List;

public class MainPresenter implements CallBack {

    private MainView mMainView;
    private final MainModel mMainModel;

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel();
    }

    public void getData(){
        mMainModel.getData(this);
    }

    @Override
    public void onSuccess(List<Bean.DatasBean> list) {
        mMainView.onSuccess(list);
    }

    @Override
    public void onFile(String msg) {
        mMainView.onFile(msg);
    }

    public void addMsg(String avatar, String title, String excerpt) {

        mMainModel.addMsg(avatar,title,excerpt);

    }

    public List<UserBean> getMsg(){
        List<UserBean> msg = mMainModel.getMsg();
        return msg;
    }

    public void delete(Long id){
        mMainModel.delete(id);
    }
}
