package com.donkor.gank4camp.ui.fragment

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.adapter.AllAdapter
import com.donkor.gank4camp.mvp.contract.CommonContract
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.mvp.persenter.AllPresenter
import com.donkor.gank4camp.ui.commom.BaseFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_common.*

/**
 * 全部
 * Created by Donkor on 2017/12/19.
 */
class AllFragment : BaseFragment(), CommonContract.View , OnRefreshListener, OnLoadmoreListener {

    private var mIsRefresh: Boolean = false
    private var mIsLoadMoreRefresh: Boolean = false
    lateinit private var mPresenter: AllPresenter
    lateinit private var mAdapter: AllAdapter
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

    override fun getLayoutResources(): Int {
        return R.layout.fragment_common
    }

    override fun initView() {
        mPresenter = AllPresenter(context, this)
        mPresenter.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = AllAdapter(context, mList)
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

    override fun loadData() {
        if (mList.size == 0)
            mPresenter.start()
    }
}

