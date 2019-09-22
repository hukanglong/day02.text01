package com.example.day02text01.mvp.model;

import com.example.day02text01.ApiService;
import com.example.day02text01.CallBack;
import com.example.day02text01.beans.Bean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {

    public void getData(CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Bean> observable = apiService.getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        if(bean!=null){
                            callBack.onSuccess(bean.getBody().getResult());
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
