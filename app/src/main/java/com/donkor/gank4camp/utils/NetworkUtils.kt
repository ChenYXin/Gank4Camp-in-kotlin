package com.donkor.gank4camp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 *
 * Created by Donkor on 2017/12/19.
 */
object NetworkUtils {
    fun isNetConneted(context: Context):Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo?= connectManager.activeNetworkInfo
        return if(networkInfo==null){
            false
        }else{
            networkInfo.isAvailable&& networkInfo.isConnected
        }
    }
}