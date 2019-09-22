package com.example.day02text01;

import com.example.day02text01.beans.Bean;
import com.example.day02text01.beans.PartBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //https://api.yunxuekeji.cn/yunxue_app_api/content/getData/30/66597/1/10
    public String url = "https://api.yunxuekeji.cn/";
    @GET("yunxue_app_api/content/getData/30/66597/1/10")
    Observable<Bean> getData();

    //https://api.yunxuekeji.cn/yunxue_app_api/teacher/getTeacherPower/191
    public String partUrl = "https://api.yunxuekeji.cn/";
    @GET("yunxue_app_api/teacher/getTeacherPower/{id}")
    Observable<PartBean> getData(@Path("id") int id);
}
