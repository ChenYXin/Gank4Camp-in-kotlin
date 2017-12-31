package com.donkor.gank4camp.mvp.model

import android.content.Context
import com.donkor.gank4camp.mvp.model.bean.GankBean
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
abstract class GankBaseModel {
    protected val gankTypeAll:String="all"
    protected val gankTypeAndroid:String="Android"
    protected val gankTypeIos:String="iOS"
    protected val gankTypeApp:String="App"
    protected val gankTypeExpand:String="拓展资源"
    protected val gankTypeGirl:String="福利"
    protected val gankTypeJs:String="前端"
    protected val gankTypeOther:String="瞎推荐"
    protected val gankTypeVideo:String="休息视频"

    abstract fun loadData(context: Context?,count: String?):Observable<GankBean>?
    abstract fun loadMoreData(context: Context, count:String?, page: String?):Observable<GankBean>?
}