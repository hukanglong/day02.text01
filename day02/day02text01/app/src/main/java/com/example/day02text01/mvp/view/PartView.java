package com.example.day02text01.mvp.view;

import com.example.day02text01.beans.PartBean;

import java.util.List;

public interface PartView {

    void onSuccess( List<PartBean.BodyBean.ResultBean> list);
    void onFile(String msg);

}
