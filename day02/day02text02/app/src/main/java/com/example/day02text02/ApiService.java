package com.example.day02text02;

import com.example.day02text02.beans.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    //http://static.owspace.com/?c=api&a=getList&page_id=0
    public String url = "http://static.owspace.com/";
    @GET("?c=api&a=getList&page_id=0")
    Observable<Bean> getData();

}
