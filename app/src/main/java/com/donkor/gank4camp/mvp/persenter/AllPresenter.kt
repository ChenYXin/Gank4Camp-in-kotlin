package com.donkor.gank4camp.mvp.persenter

import android.content.Context
import android.util.Log
import com.donkor.gank4camp.applySchedulers
import com.donkor.gank4camp.mvp.contract.AllContract
import com.donkor.gank4camp.mvp.model.bean.AllModel
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import io.reactivex.Observable

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class AllPresenter(context: Context, view: AllContract.View) : AllContract.Presenter {
    private var mContext: Context? = null
    private var mView: AllContract.View? = null
    private val mModel: AllModel by lazy {
        AllModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<CommonBean>? = mContext?.let { mModel.loadData(it, "10") }
        observable?.applySchedulers()?.subscribe { commonBean: CommonBean -> mView?.setData(commonBean) }
    }

    fun moreData(count: String?, page: String?) {
        val observable: Observable<CommonBean>? = mContext?.let { mModel.loadMoreData(it, count, page) }
        observable?.applySchedulers()?.subscribe { commonBean: CommonBean -> mView?.setData(commonBean) }
    }
}