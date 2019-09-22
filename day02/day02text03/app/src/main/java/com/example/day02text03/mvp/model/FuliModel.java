package com.example.day02text03.mvp.model;

import com.example.day02text03.ApiService;
import com.example.day02text03.CallBack;
import com.example.day02text03.beas.FuliBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuliModel {

    public void getData(CallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.fuliUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FuliBean> observable = apiService.getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        if(fuliBean!=null){
                            callBack.onSuccess(fuliBean.getResults());
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
