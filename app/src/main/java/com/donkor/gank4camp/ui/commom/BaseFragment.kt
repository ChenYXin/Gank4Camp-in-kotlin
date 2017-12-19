package com.donkor.gank4camp.ui.commom

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * Created by Donkor on 2017/12/18.
 */
abstract class BaseFragment : Fragment() {
    var rootView: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * 判断该Fragment是否显示在用户面前
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden)
            loadData()

    }

    abstract fun getLayoutResources(): Int
    abstract fun initView()
    /**
     * 懒加载，当前Fragment显示的时候才进行网络请求
     * 如果数据不需要每次都刷新，可以先判断数据是否存在
     * 数据不存在 -> 进行网络请求    数据存在 -> 什么都不做
     */
    abstract fun loadData()

}