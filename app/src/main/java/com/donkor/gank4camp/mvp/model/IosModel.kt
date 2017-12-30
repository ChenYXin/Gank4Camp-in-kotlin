package com.donkor.gank4camp.mvp.model

import android.content.Context
import com.donkor.gank4camp.mvp.model.bean.GankBean
import com.donkor.gank4camp.network.ApiService
import com.donkor.gank4camp.network.RetrofitClient
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class IosModel {
    private val type:String="iOS"

    private fun getApiService(context: Context?): ApiService? {
        val retrofitClient = context?.let { RetrofitClient.getInstance(it, ApiService.BASE_URL) }
        return retrofitClient?.create(ApiService::class.java)
    }

    fun loadData(context: Context?, count: String?): Observable<GankBean>? {
        return getApiService(context)?.getData(type,count)
    }

    fun loadMoreData(context: Context, count: String?, page: String?): Observable<GankBean>? {
        return getApiService(context)?.getMoreData(type,count, page)
    }
}