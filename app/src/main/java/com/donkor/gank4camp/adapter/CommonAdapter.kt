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
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.utils.ImageLoadUtils

/**
 * Created by donkor on 2017/12/20.
 */
class CommonAdapter(context: Context, list: MutableList<CommonBean.Result>?) : RecyclerView.Adapter<CommonAdapter.CommonHolder>() {
    private var mContext: Context? = null
    private var mList: MutableList<CommonBean.Result>? = null
    private var inflater: LayoutInflater? = null
//    private val TYPE_ITEM: Int? = 0
//    private val TYPE_FOOTER: Int? = 1


    init {
        mContext = context
        mList = list
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (position + 1 == itemCount) {
//            TYPE_FOOTER!!
//        } else {
//            TYPE_ITEM!!
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommonHolder {
//        when (viewType) {
//            TYPE_ITEM ->
                return CommonHolder(inflater?.inflate(R.layout.item_common, parent, false))
//            TYPE_FOOTER -> return FooterViewHolder(inflater?.inflate(R.layout.load_more, parent, false))
//        }
//        return null
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommonHolder?, position: Int) {

//        if (holder is CommonHolder) {
            val desc: String? = mList?.get(position)?.desc
            val url: String? = mList?.get(position)?.url
            val author: String? = mList?.get(position)?.who
            holder?.tvContent?.text = Html.fromHtml("<font color=\"${mContext?.resources?.getColor(R.color.blue)}\"><u><a href='$url'>$desc</a></u></font><font color=\"gray\">[ $author ]</font>")
            holder?.tvContent?.movementMethod = LinkMovementMethod.getInstance()

            if (null != mList?.get(position)?.images && mList?.get(position)?.images?.size != 0) {
                ImageLoadUtils.display(mContext, holder?.ivContentPic, mList?.get(position)?.images!![0])
            } else {
                holder?.ivContentPic?.visibility = View.GONE
            }
//        }

    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView? = null
        var ivContentPic: ImageView? = null

        init {
            tvContent = itemView?.findViewById(R.id.tv_content)
            ivContentPic = itemView?.findViewById(R.id.iv_content_pic)
        }
    }
}