package com.garyhu.citypickerdemo.model.net;

import android.database.Observable;

import com.garyhu.citypickerdemo.model.CityNewBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 作者： garyhu.
 * 时间： 2017/1/13.
 */

public interface ServerApi {

    /**
     * 城市信息
     *
     * @return
     */
    @POST("Public/get_address")
    Call<ServerResponse<CityNewBean>> getHomePage();

//    @POST("***************************")
//    Observable<MDJHttpResponse<StoreMessageBean>> getHomePage(@Body HashMap<String, String> map);
}
