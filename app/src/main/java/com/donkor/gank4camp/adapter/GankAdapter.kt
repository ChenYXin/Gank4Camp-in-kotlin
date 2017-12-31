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
import android.widget.TextView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.mvp.model.bean.GankBean
import com.donkor.gank4camp.utils.ImageLoadUtils
import pl.droidsonroids.gif.GifImageView

/**
 * Created by donkor on 2017/12/20.
 */
class GankAdapter internal constructor(context: Context, list: MutableList<GankBean.Result>?) : RecyclerView.Adapter<GankAdapter.CommonHolder>() {
    private var mContext: Context? = null
    private var mList: MutableList<GankBean.Result>? = null
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
        val desc: String? = mList?.get(position)?.desc
        val url: String? = mList?.get(position)?.url
        val author: String? = mList?.get(position)?.who
        holder?.tvContent?.text = Html.fromHtml("<font color=\"${mContext?.resources?.getColor(R.color.blue)}\"><u><a href='$url'>$desc</a></u></font><font color=\"gray\">[ $author ]</font>")
        holder?.tvContent?.movementMethod = LinkMovementMethod.getInstance()

        if (null != mList?.get(position)?.images && mList?.get(position)?.images?.size != 0) {
            holder?.ivContentPic?.visibility = View.VISIBLE
            ImageLoadUtils.display(mContext, holder?.ivContentPic, mList?.get(position)?.images!![0])
        } else {
            holder?.ivContentPic?.visibility = View.GONE
        }
    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView? = null
        var ivContentPic: GifImageView? = null

        init {
            tvContent = itemView?.findViewById(R.id.tv_content)
            ivContentPic = itemView?.findViewById(R.id.iv_content_pic)
        }
    }
}