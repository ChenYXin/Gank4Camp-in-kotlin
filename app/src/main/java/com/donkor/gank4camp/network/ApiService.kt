package com.donkor.gank4camp.network

import com.donkor.gank4camp.mvp.model.bean.CommonBean
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

    //获取第一页数据
    @GET("{type}/{count}/1")
    fun getData(@Path("type")type:String,@Path("count") count: String?):Observable<CommonBean>?

    //获取第一页之后的数据
    @GET("{type}/{count}/{page}")
    fun getMoreData(@Path("type")type:String,@Path("count") count: String?, @Path("page") page: String?): Observable<CommonBean>
}