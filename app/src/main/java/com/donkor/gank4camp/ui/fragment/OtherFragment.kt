package com.donkor.gank4camp.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.donkor.gank4camp.R
import com.donkor.gank4camp.adapter.CommonAdapter
import com.donkor.gank4camp.mvp.contract.CommonContract
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.mvp.persenter.OtherPresenter
import com.donkor.gank4camp.ui.commom.BaseFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_common.*

/**
 *
 * Created by Donkor on 2017/12/18.
 */
class OtherFragment : BaseFragment() , CommonContract.View, OnRefreshListener, OnLoadmoreListener {

    private var mIsRefresh: Boolean = false
    private var mIsLoadMoreRefresh: Boolean = false
    lateinit private var mPresenter: OtherPresenter
    lateinit private var mAdapter: CommonAdapter
    private var mList = ArrayList<CommonBean.Result>()
    private val mCount: String? = "10"
    private var mPage: Int? = 1

    override fun setData(bean: CommonBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            smartRefreshLayout.finishRefresh()
            if (mList.size > 0) {
                mPage = 1
                mList.clear()
            }
        }
        if (mIsLoadMoreRefresh) {
            mIsLoadMoreRefresh = false
            smartRefreshLayout.finishLoadmore()
        }
        bean.results.forEach {
            mList.add(it)
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun loadData() {
        //懒加载，当前Fragment显示的时候才进行网络请求
        //如果数据不需要每次都刷新，可以先判断数据是否存在
        //数据不存在 -> 进行网络请求    数据存在 -> 什么都不做
        if(mList.size==0)
            mPresenter.start()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_common
    }

    override fun initView() {
        mPresenter = OtherPresenter(context, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = CommonAdapter(context, mList)
        recyclerView.adapter = mAdapter


        smartRefreshLayout.setOnRefreshListener(this)
        smartRefreshLayout.setOnLoadmoreListener(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter.start()
        }
    }

    override fun onLoadmore(refreshLayout: RefreshLayout?) {
        if (!mIsLoadMoreRefresh) {
            mIsLoadMoreRefresh = true
            mPage = mPage!! + 1
            mPresenter.moreData(mCount, mPage.toString())
        }
    }
}