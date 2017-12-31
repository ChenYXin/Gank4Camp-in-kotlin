package com.donkor.gank4camp.mvp.model

import android.content.Context
import com.donkor.gank4camp.mvp.model.bean.GankBean
import com.donkor.gank4camp.mvp.model.bean.WeatherBean
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
abstract class WeatherBaseModel {
    protected val weatherKey:String="573d1ed0b83f4e978535b7c05fa503e0"

    abstract fun loadData(context: Context?, location: String?): Observable<WeatherBean>?
//    abstract fun loadMoreData(context: Context, location:String?, page: String?): Observable<GankBean>?
}