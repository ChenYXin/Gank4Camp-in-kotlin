package com.donkor.gank4camp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.utils.ImageLoadUtils

/**
 * Created by donkor on 2017/12/20.
 */
class GirlAdapter(context: Context, list: MutableList<CommonBean.Result>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mContext: Context? = null
    private var mList: MutableList<CommonBean.Result>? = null
    private var inflater: LayoutInflater? = null
    private val TYPE_ITEM: Int? = 0
    private val TYPE_FOOTER: Int? = 1

    //上拉加载更多
    val PULLUP_LOAD_MORE: Int? = 0
    //正在加载中
    val LOAD_MORE: Int? = 1
    //没有加载更多 隐藏
    val NO_LOAD_MORE: Int? = 2

    //上拉加载更多状态-默认为0
    private var mLoadMoreStatus: Int? = 0

    init {
        mContext = context
        mList = list
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position + 1 == itemCount) {
            TYPE_FOOTER!!
        } else {
            TYPE_ITEM!!
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            TYPE_ITEM -> return CommonHolder(inflater?.inflate(R.layout.item_girl, parent, false))
            TYPE_FOOTER -> return FooterViewHolder(inflater?.inflate(R.layout.load_more, parent, false))
        }
        return null
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is CommonHolder) {
            holder.tvPicDes?.text = mList?.get(position)?.desc
            ImageLoadUtils.display(mContext, holder.ivPic, mList?.get(position)?.url)

        } else if (holder is FooterViewHolder) {
            when (mLoadMoreStatus) {
                PULLUP_LOAD_MORE -> holder.tvLoadText?.text = "上拉加载更多"
                LOAD_MORE -> holder.tvLoadText?.text = "正在加载更多..."
                NO_LOAD_MORE -> holder.linLoadLayout?.visibility = View.GONE
            }
        }

    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var ivPic: ImageView? = null
        var tvPicDes: TextView? = null

        init {
            ivPic = itemView?.findViewById(R.id.iv_pic)
            tvPicDes = itemView?.findViewById(R.id.tv_pic_des)
        }
    }

    class FooterViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var pbLoad: ProgressBar? = null
        var tvLoadText: TextView? = null
        var linLoadLayout: LinearLayout? = null

        init {
            pbLoad = itemView?.findViewById(R.id.pb_load)
            tvLoadText = itemView?.findViewById(R.id.tv_load_text)
            linLoadLayout = itemView?.findViewById(R.id.lin_load_layout)
        }
    }

    fun changeMoreStatus(status: Int) {
        mLoadMoreStatus = status
        notifyDataSetChanged()
    }
}