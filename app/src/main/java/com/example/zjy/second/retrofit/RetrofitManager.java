package com.example.zjy.second.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZJY on 2017/11/29.
 */

public class RetrofitManager {

    public static APIService getAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://119.23.237.193/hzl/Second/public/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIService.class);
    }

}
