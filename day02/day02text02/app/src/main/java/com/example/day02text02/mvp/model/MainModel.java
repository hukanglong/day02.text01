package com.example.day02text02.mvp.model;

import com.example.day02text02.ApiService;
import com.example.day02text02.BaseApp;
import com.example.day02text02.CallBack;
import com.example.day02text02.beans.Bean;
import com.example.day02text02.beans.UserBean;
import com.example.day02text02.beans.UserBeanDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {

    private UserBeanDao mUserBeanDao;

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
                            callBack.onSuccess(bean.getDatas());
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

    public void addMsg(String avatar, String title, String excerpt) {


        mUserBeanDao = new BaseApp().getInstance().getDaoSession().getUserBeanDao();
        mUserBeanDao.insert(new UserBean(null,avatar,title,excerpt));

    }

    public List<UserBean> getMsg(){
        mUserBeanDao = new BaseApp().getInstance().getDaoSession().getUserBeanDao();
        List<UserBean> userBeans = mUserBeanDao.loadAll();
        return userBeans;
    }

    public void delete(Long id) {
        mUserBeanDao = BaseApp.getInstance().getDaoSession().getUserBeanDao();
        mUserBeanDao.deleteByKey(id);
    }
}
