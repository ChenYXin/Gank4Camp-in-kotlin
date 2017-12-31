package com.donkor.gank4camp.network

import com.donkor.gank4camp.mvp.model.bean.GankBean
import com.donkor.gank4camp.mvp.model.bean.WeatherBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by donkor on 2017/12/20.
 */
interface ApiService {
    companion object {
        val Gank_BASE_URL: String
            get() = "http://gank.io/api/data/"
        val WEATHER_BASE_URL: String
            get() = "https://free-api.heweather.com/s6/"
    }

    //获取Gank第一页数据
    @GET("{type}/{count}/1")
    fun getGankData(@Path("type") type: String, @Path("count") count: String?): Observable<GankBean>?

    //获取Gank第一页之后的数据
    @GET("{type}/{count}/{page}")
    fun getGankMoreData(@Path("type") type: String, @Path("count") count: String?, @Path("page") page: String?): Observable<GankBean>

    //获取Gank第一页数据
    @POST("weather")
    fun getWeatherData(@Query("key") key: String, @Query("location") location: String?): Observable<WeatherBean>?
}