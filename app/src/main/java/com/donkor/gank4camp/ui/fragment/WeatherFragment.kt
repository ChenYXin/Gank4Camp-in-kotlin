package com.donkor.gank4camp.ui.fragment

import com.donkor.gank4camp.R
import com.donkor.gank4camp.ui.commom.CommonFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_weather.*

/**
 * Created by donkor on 2017/12/29.
 */
class WeatherFragment : CommonFragment() {

    override fun getLayoutResources(): Int {
        return R.layout.fragment_weather
    }

    override fun initView() {
//        smartRefreshLayout.setOnRefreshListener(this)
    }

    override fun loadData() {
    }
}