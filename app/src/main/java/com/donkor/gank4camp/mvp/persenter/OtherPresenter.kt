package com.donkor.gank4camp.mvp.persenter

import android.content.Context
import com.donkor.gank4camp.applySchedulers
import com.donkor.gank4camp.mvp.contract.CommonContract
import com.donkor.gank4camp.mvp.model.OtherModel
import com.donkor.gank4camp.mvp.model.bean.GankBean
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class OtherPresenter(context: Context, view: CommonContract.View) : CommonContract.Presenter {
    private var mContext: Context? = null
    private var mView: CommonContract.View? = null
    private val mModel: OtherModel by lazy {
        OtherModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<GankBean>? = mContext?.let { mModel.loadData(it, "20") }
        observable?.applySchedulers()?.subscribe { commonBean: GankBean -> mView?.setData(commonBean) }
    }

    fun moreData(count: String?, page: String?) {
        val observable: Observable<GankBean>? = mContext?.let { mModel.loadMoreData(it, count, page) }
        observable?.applySchedulers()?.subscribe { commonBean: GankBean -> mView?.setData(commonBean) }
    }
}