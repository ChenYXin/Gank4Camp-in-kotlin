package com.donkor.gank4camp.mvp.contract

import com.donkor.gank4camp.base.BasePresenter
import com.donkor.gank4camp.base.BaseView
import com.donkor.gank4camp.mvp.model.bean.CommonBean

/**
 *
 * Created by Donkor on 2017/12/19.
 */
interface AllContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: CommonBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}