package com.example.day02text01;

import com.example.day02text01.beans.Bean;

import java.util.List;

public interface CallBack {

    void onSuccess(List<Bean.BodyBean.ResultBean> list);
    void onFile(String msg);

}
