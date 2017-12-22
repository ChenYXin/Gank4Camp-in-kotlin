package com.donkor.gank4camp.mvp.model.bean

import android.content.Context
import android.util.Log
import com.donkor.gank4camp.network.ApiService
import com.donkor.gank4camp.network.RetrofitClient
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class AllModel {

    private fun getApiService(context: Context?): ApiService? {
        val retrofitClient = context?.let { RetrofitClient.getInstance(it, ApiService.BASE_URL) }
        return retrofitClient?.create(ApiService::class.java)
    }

    fun loadData(context: Context?, count: String?): Observable<CommonBean>? {
        return getApiService(context)?.getAllData(count)
    }

    fun loadMoreData(context: Context, count: String?, page: String?): Observable<CommonBean>? {
        return getApiService(context)?.getAllMoreData(count, page)
    }
}