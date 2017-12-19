package com.donkor.gank4camp.network

import android.content.Context
import android.util.Log
import com.donkor.gank4camp.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class CacheInterceptor(context: Context) : Interceptor {
    private val mContext = context
    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
        return if (NetworkUtils.isNetConneted(mContext)) {
            val response = chain?.proceed(request)
            // read from cache for 60 s
            val maxAge = 60
            val cacheControl = request?.cacheControl().toString()
            Log.e("CacheInterceptor", "6s load cahe" + cacheControl)
            response?.newBuilder()?.removeHeader("Pragam")?.removeHeader("Cache-Control")
                    ?.header("Cache-Control", "public,max-age=" + maxAge)?.build()
        } else {
            Log.e("CacheInterceptor", "no network load cache")
            request = request?.newBuilder()?.cacheControl(CacheControl.FORCE_CACHE)?.build()
            val response = chain?.proceed(request)
            //set cache times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            response?.newBuilder()?.removeHeader("Pragma")?.removeHeader("Cache-Control")
                    ?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)?.build()
        }
    }
}