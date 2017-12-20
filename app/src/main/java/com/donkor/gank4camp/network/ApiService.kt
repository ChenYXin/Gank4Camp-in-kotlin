package com.donkor.gank4camp.network

import com.donkor.gank4camp.mvp.model.bean.AllBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by donkor on 2017/12/20.
 */
interface ApiService {
    companion object {
        val BASE_URL: String
            get() = "http://gank.io/api/data/"
    }

    //获取所有第一页数据
    @GET("all/{count}/1")
    fun getAllData(@Path("count") count: String?): Observable<AllBean>

    //获取所有第一页之后的数据
    @GET("all/{count}/{page}")
    fun getAllMoreData(@Path("count")count: String?, @Path("page")page: String?): Observable<AllBean>
}