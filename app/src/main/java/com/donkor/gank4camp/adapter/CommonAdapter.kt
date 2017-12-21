package com.donkor.gank4camp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkor.gank4camp.mvp.model.bean.AllBean

/**
 * Created by donkor on 2017/12/20.
 */
class CommonAdapter(context: Context, list: MutableList<AllBean>?) : RecyclerView.Adapter<CommonAdapter.CommonHolder>() {
    private var mContext: Context? = null
    private var mList: MutableList<AllBean>? = null
    private var inflater: LayoutInflater? = null

    init {
        mContext = context
        mList = list
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommonHolder {

    }

    override fun onBindViewHolder(holder: CommonHolder?, position: Int) {

    }

    class CommonHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {

    }
}