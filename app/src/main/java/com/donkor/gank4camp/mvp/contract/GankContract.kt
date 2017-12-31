package com.donkor.gank4camp.mvp.contract

import com.donkor.gank4camp.base.BasePresenter
import com.donkor.gank4camp.base.BaseView
import com.donkor.gank4camp.mvp.model.bean.GankBean

/**
 *
 * Created by Donkor on 2017/12/19.
 */
interface GankContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: GankBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}