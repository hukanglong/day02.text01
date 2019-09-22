package com.example.day02text02;

import com.example.day02text02.beans.Bean;

import java.util.List;

public interface CallBack {

    void onSuccess(List<Bean.DatasBean> list);
    void onFile(String msg);

}
