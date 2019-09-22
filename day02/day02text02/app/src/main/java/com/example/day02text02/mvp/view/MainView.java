package com.example.day02text02.mvp.view;

import com.example.day02text02.beans.Bean;

import java.util.List;

public interface MainView {

    void onSuccess(List<Bean.DatasBean> list);
    void onFile(String msg);

}
