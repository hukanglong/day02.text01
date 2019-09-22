package com.example.day02text03;

import com.example.day02text03.beas.FuliBean;

import java.util.List;

public interface CallBack {

    void onSuccess(List<FuliBean.ResultsBean> list);
    void onFile(String msg);

}
