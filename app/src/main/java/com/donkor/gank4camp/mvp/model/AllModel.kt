package com.donkor.gank4camp.mvp.model.bean

import android.content.Context
import android.util.Log
import com.donkor.gank4camp.mvp.model.GankBaseModel
import com.donkor.gank4camp.network.ApiService
import com.donkor.gank4camp.network.RetrofitClient
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class AllModel : GankBaseModel() {

    private fun getApiService(context: Context?): ApiService? {
        Log.e("asd", "AllModel " + ApiService.Gank_BASE_URL)
        val retrofitClient = context?.let { RetrofitClient.getGankInstance(it, ApiService.Gank_BASE_URL) }
        return retrofitClient?.create(ApiService::class.java)
    }

    override fun loadData(context: Context?, count: String?): Observable<GankBean>? {
        return getApiService(context)?.getGankData(gankTypeAll, count)
    }

    override fun loadMoreData(context: Context, count: String?, page: String?): Observable<GankBean>? {
        return getApiService(context)?.getGankMoreData(gankTypeAll, count, page)
    }
}