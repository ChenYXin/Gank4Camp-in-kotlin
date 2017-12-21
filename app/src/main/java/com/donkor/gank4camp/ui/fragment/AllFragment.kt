package com.donkor.gank4camp.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.donkor.gank4camp.R
import com.donkor.gank4camp.R.id.recyclerView
import com.donkor.gank4camp.R.id.refreshLayout
import com.donkor.gank4camp.adapter.CommonAdapter
import com.donkor.gank4camp.mvp.contract.AllContract
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.mvp.persenter.AllPresenter
import com.donkor.gank4camp.ui.commom.BaseFragment
import kotlinx.android.synthetic.main.fragment_all.*

/**
 * 全部
 * Created by Donkor on 2017/12/19.
 */
class AllFragment : BaseFragment(), AllContract.View, SwipeRefreshLayout.OnRefreshListener {
    private var mIsRefresh: Boolean = false
    private var mPresenter: AllPresenter? = null
    private var mAdapter: CommonAdapter? = null
    private var mList = ArrayList<CommonBean.Result>()
    private val mCount: String? = "20"
    private var mPage: Int? = 1

    override fun setData(bean: CommonBean) {
        Log.e("asd", "============================")
        Log.e("asd", "bean :" + bean.results.size)
        Log.e("asd", "bean :" + bean.results[1].who)
        Log.e("asd", "mIsRefresh ------ " + mIsRefresh)
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            Log.e("asd", "mList.size " + mList.size)
            if (mList.size > 0) {
                mList.clear()
            }
        }
//        mList.add(bean.results)
//        bean.results.forEach { mList.add(it) }
        bean.component2().forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_all
    }

    override fun initView() {
        mPresenter = AllPresenter(context, this)
        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = CommonAdapter(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int? = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == mList.size - 1) {
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

    override fun onRefresh() {
        Log.e("asd", "onRefresh --- " + !mIsRefresh)
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter?.start()
        }
    }

    override fun loadData() {
//        if (!mIsRefresh) {
//            mIsRefresh = true
//            mPresenter?.start()
//        }
    }
}

