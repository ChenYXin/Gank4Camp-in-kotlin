package com.donkor.gank4camp.mvp.persenter

import android.content.Context
import com.donkor.gank4camp.mvp.contract.AllContract

/**
 *
 * Created by Donkor on 2017/12/19.
 */
class AllPersenter(context: Context, view: AllContract.View) : AllContract.Presenter {
    private var mContext: Context? = null
    private var mView: AllContract.View? = null
//    private var mModel:AllMo

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
//        val observable: Observable<AllBean>? = mContext?.let { mModel.loadData(it, true, "0") }
    }
}