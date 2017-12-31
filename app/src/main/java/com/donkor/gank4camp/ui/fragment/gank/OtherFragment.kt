package com.donkor.gank4camp.ui.fragment.gank

import android.support.v7.widget.LinearLayoutManager
import com.donkor.gank4camp.R
import com.donkor.gank4camp.adapter.GankAdapter
import com.donkor.gank4camp.mvp.contract.GankContract
import com.donkor.gank4camp.mvp.model.bean.GankBean
import com.donkor.gank4camp.mvp.persenter.OtherPresenter
import com.donkor.gank4camp.ui.commom.GankBaseFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_gank_item.*

/**
 *
 * Created by Donkor on 2017/12/18.
 */
class OtherFragment : GankBaseFragment() , GankContract.View, OnRefreshListener, OnLoadmoreListener {

    private var mIsRefresh: Boolean = false
    private var mIsLoadMoreRefresh: Boolean = false
    lateinit private var mPresenter: OtherPresenter
    lateinit private var mAdapter: GankAdapter
    private var mList = ArrayList<GankBean.Result>()
    private val mCount: String? = "10"
    private var mPage: Int? = 1

    override fun setData(bean: GankBean) {
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

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        if (isVisible) {
            if (mList.size == 0)
                mPresenter.start()
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_gank_item
    }

    override fun initView() {
        mPresenter = OtherPresenter(context, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = GankAdapter(context, mList)
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