package com.example.day02text03.mvp.view;

import com.example.day02text03.beas.FuliBean;

import java.util.List;

public interface FuliView {

    void onSuccess(List<FuliBean.ResultsBean> list);
    void onFile(String msg);

}
