package com.donkor.gank4camp.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.donkor.gank4camp.R
import com.donkor.gank4camp.adapter.WeatherAdapter
import com.donkor.gank4camp.mvp.contract.WeatherContract
import com.donkor.gank4camp.mvp.model.bean.WeatherBean
import com.donkor.gank4camp.mvp.persenter.WeatherPresenter
import com.donkor.gank4camp.ui.commom.CommonFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * Created by donkor on 2017/12/29.
 */
class WeatherFragment : CommonFragment(), WeatherContract.View, OnRefreshListener {

    private var mIsRefresh: Boolean = false
    lateinit private var mPresenter: WeatherPresenter
    lateinit private var mAdapter: WeatherAdapter

    override fun setData(bean: WeatherBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            smartRefreshLayout.finishRefresh()
        }
        mAdapter = WeatherAdapter(context, bean)
        recyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_weather
    }

    override fun initView() {
        mPresenter = WeatherPresenter(context, this)
        recyclerView.layoutManager = LinearLayoutManager(context)

        smartRefreshLayout.setOnRefreshListener(this)
    }

    override fun loadData() {
            mPresenter.start()
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter.start()
        }
    }
}