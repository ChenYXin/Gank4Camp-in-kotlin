package com.donkor.gank4camp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.R.id.tv_content
import com.donkor.gank4camp.mvp.model.bean.CommonBean

/**
 * Created by donkor on 2017/12/20.
 */
class CommonAdapter(context: Context, list: MutableList<CommonBean.Result>?) : RecyclerView.Adapter<CommonAdapter.CommonHolder>() {
    private var mContext: Context? = null
    private var mList: MutableList<CommonBean.Result>? = null
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
        return CommonHolder(inflater?.inflate(R.layout.item_common, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommonHolder?, position: Int) {
        holder?.tvContent?.text = mList?.get(position)?.desc + "[ " + mList?.get(position)?.who + " ]"
    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView? = null

        init {
            tvContent = itemView?.findViewById(tv_content)
        }
    }
}