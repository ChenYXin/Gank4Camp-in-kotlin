package com.donkor.gank4camp.ui.fragment

import com.donkor.gank4camp.R
import com.donkor.gank4camp.mvp.contract.AllContract
import com.donkor.gank4camp.mvp.model.bean.AllBean
import com.donkor.gank4camp.mvp.persenter.AllPersenter
import com.donkor.gank4camp.ui.commom.BaseFragment

/**
 *全部
 * Created by Donkor on 2017/12/19.
 */
class AllFragment : BaseFragment(), AllContract.View {
    private var allPresenter: AllPersenter? = null
    override fun setData(bean: AllBean) {
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_all
    }

    override fun initView() {
    }

    override fun loadData() {
    }
}