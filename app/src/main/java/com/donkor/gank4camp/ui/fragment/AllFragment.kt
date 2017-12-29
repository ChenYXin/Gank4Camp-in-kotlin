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
import kotlinx.android.synthetic.main.fragment_all.*

/**
 * 全部
 * Created by Donkor on 2017/12/19.
 */
class AllFragment : BaseFragment(), CommonContract.View, SwipeRefreshLayout.OnRefreshListener {
    private var mIsRefresh: Boolean = false
    lateinit private var mPresenter: AllPresenter
    lateinit private var mAdapter: AllAdapter
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
        mAdapter.changeMoreStatus(mAdapter.PULLUP_LOAD_MORE!!)
        mAdapter.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_all
    }

    override fun initView() {
        mPresenter = AllPresenter(context, this)
        mPresenter.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = AllAdapter(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        //下拉刷新进度条颜色
        refreshLayout.setColorSchemeColors(Color.rgb(79, 148, 205))
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int? = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == mList.size - 1) {
                    mAdapter.changeMoreStatus(mAdapter.LOAD_MORE!!)
                    mPage = mPage!! + 1
                    mPresenter.moreData(mCount, mPage.toString())
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
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter.start()
        }
    }

    override fun loadData() {
//        Log.e("asd", "size : " + mList.size)
        if (mList.size == 0)
            mPresenter.start()
    }
}

