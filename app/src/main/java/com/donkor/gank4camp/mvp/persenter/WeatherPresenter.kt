package com.donkor.gank4camp.mvp.persenter

import android.content.Context
import com.donkor.gank4camp.applySchedulers
import com.donkor.gank4camp.mvp.contract.WeatherContract
import com.donkor.gank4camp.mvp.model.WeatherModel
import com.donkor.gank4camp.mvp.model.bean.WeatherBean
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class WeatherPresenter(context: Context, view: WeatherContract.View) : WeatherContract.Presenter {

    private var mContext: Context? = null
    private var mView: WeatherContract.View? = null
    private val mModel: WeatherModel by lazy {
        WeatherModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<WeatherBean>? = mContext?.let { mModel.loadData(it,"深圳") }
        observable?.applySchedulers()?.subscribe { commonBean: WeatherBean -> mView?.setData(commonBean) }
    }

//    fun moreData(count: String?, page: String?) {
//        val observable: Observable<GankBean>? = mContext?.let { mModel.loadMoreData(it, count, page) }
//        observable?.applySchedulers()?.subscribe { commonBean: GankBean -> mView?.setData(commonBean) }
//    }
}