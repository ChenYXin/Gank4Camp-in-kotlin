package com.donkor.gank4camp.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.adapter.CommonAdapter
import com.donkor.gank4camp.mvp.contract.CommonContract
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.mvp.persenter.ExpandPresenter
import com.donkor.gank4camp.mvp.persenter.VideoPresenter
import com.donkor.gank4camp.ui.commom.BaseFragment
import kotlinx.android.synthetic.main.fragment_common.*

/**
 *
 * Created by Donkor on 2017/12/18.
 */
class ExpandFragment : BaseFragment() , CommonContract.View, SwipeRefreshLayout.OnRefreshListener {

    private var mIsRefresh: Boolean = false
    private var mPresenter: ExpandPresenter? = null
    private var mAdapter: CommonAdapter? = null
    private var mList = ArrayList<CommonBean.Result>()
    private val mCount: String? = "10"
    private var mPage: Int? = 1

    override fun setData(bean: CommonBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mPage = 1
                mList.clear()
            }
        }
        bean.results.forEach {
            mList.add(it)
        }
        mAdapter?.changeMoreStatus(mAdapter?.PULLUP_LOAD_MORE!!)
        mAdapter?.notifyDataSetChanged()
    }

    override fun loadData() {
        //懒加载，当前Fragment显示的时候才进行网络请求
        //如果数据不需要每次都刷新，可以先判断数据是否存在
        //数据不存在 -> 进行网络请求    数据存在 -> 什么都不做
        if(mList.size==0)
            mPresenter?.start()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_common
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter?.start()
        }
    }

    override fun initView() {
        mPresenter = ExpandPresenter(context, this)
//        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = CommonAdapter(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int? = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == mList.size - 1) {
                    mAdapter?.changeMoreStatus(mAdapter?.LOAD_MORE!!)
                    mPage = mPage!! + 1
                    mPresenter?.moreData(mCount, mPage.toString())
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
        })
    }
}