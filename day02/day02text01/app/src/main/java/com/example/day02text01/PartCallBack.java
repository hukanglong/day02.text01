package com.example.day02text01;

import com.example.day02text01.beans.PartBean;

import java.util.List;

public interface PartCallBack {

    void onSuccess( List<PartBean.BodyBean.ResultBean> list);
    void onFile(String msg);

}
