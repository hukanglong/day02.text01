package com.example.day02text01.mvp.view;

import com.example.day02text01.beans.Bean;

import java.util.List;

public interface MainView {

    void onSuccess(List<Bean.BodyBean.ResultBean> list);
    void onFile(String msg);

}
