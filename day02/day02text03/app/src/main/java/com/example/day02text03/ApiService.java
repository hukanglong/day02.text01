package com.example.day02text03;

import com.example.day02text03.beas.FuliBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1
    public String fuliUrl = "http://gank.io/";
    @GET("api/data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<FuliBean> getData();

}
