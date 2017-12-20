package com.donkor.gank4camp.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.donkor.gank4camp.R
import com.donkor.gank4camp.mvp.contract.AllContract
import com.donkor.gank4camp.mvp.model.bean.AllBean
import com.donkor.gank4camp.mvp.persenter.AllPresenter
import com.donkor.gank4camp.ui.commom.BaseFragment
import kotlinx.android.synthetic.main.fragment_all.*

/**
 * 全部
 * Created by Donkor on 2017/12/19.
 */
class AllFragment : BaseFragment(), AllContract.View {
    private var mPresenter: AllPresenter? = null
    override fun setData(bean: AllBean) {
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_all
    }

    override fun initView() {
        /**
         * mPresenter = HomePresenter(context, this)
        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdatper(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
        var lastPositon = layoutManager.findLastVisibleItemPosition()
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
        if (data != null) {
        mPresenter?.moreData(data)
        }

        }
        }
        })
         */
        mPresenter = AllPresenter(context,this)
        mPresenter?.start()
        recyclerView.layoutManager=LinearLayoutManager(context)

    }

    override fun loadData() {
    }
}