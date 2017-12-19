package com.donkor.gank4camp.ui.fragment

import com.donkor.gank4camp.R
import com.donkor.gank4camp.ui.commom.BaseFragment


/**
 *
 * Created by Donkor on 2017/12/18.
 */
class FirstFragment : BaseFragment() {
    override fun loadData() {
        //懒加载，当前Fragment显示的时候才进行网络请求
        //如果数据不需要每次都刷新，可以先判断数据是否存在
        //数据不存在 -> 进行网络请求    数据存在 -> 什么都不做
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_frist
    }

    override fun initView() {
    }
}