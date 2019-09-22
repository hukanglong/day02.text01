package com.example.day02text01.mvp.model;

import android.util.Log;

import com.example.day02text01.ApiService;
import com.example.day02text01.PartCallBack;
import com.example.day02text01.beans.PartBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PartModel {

    public void getData(PartCallBack callBack,int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.partUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<PartBean> data = apiService.getData(id);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PartBean partBean) {
                        if(partBean!=null){
                            callBack.onSuccess(partBean.getBody().getResult());
                        }else {
                            callBack.onFile("无法请求到数据");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFile(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
