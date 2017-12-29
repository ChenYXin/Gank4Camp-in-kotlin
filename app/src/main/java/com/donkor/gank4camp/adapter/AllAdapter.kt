package com.donkor.gank4camp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
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
import com.donkor.gank4camp.R.id.*
import com.donkor.gank4camp.mvp.model.bean.CommonBean
import com.donkor.gank4camp.utils.ImageLoadUtils

/**
 * Created by donkor on 2017/12/20.
 */
class AllAdapter(context: Context, list: MutableList<CommonBean.Result>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            TYPE_ITEM -> return CommonHolder(inflater?.inflate(R.layout.item_all, parent, false))
            TYPE_FOOTER -> return FooterViewHolder(inflater?.inflate(R.layout.load_more, parent, false))
        }
        return null
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is CommonHolder) {
            if (mList?.get(position)?.type.equals("福利")) {
                holder.llPic?.visibility = View.VISIBLE
                holder.tvContent?.visibility = View.GONE
                holder.ivContentPic?.visibility = View.GONE
                holder.tvPicDes?.text = mList?.get(position)?.desc
                holder.ivContentType?.visibility = View.GONE
                ImageLoadUtils.display(mContext, holder.ivPic, mList?.get(position)?.url)
            } else {
                holder.llPic?.visibility = View.GONE
                holder.tvContent?.visibility = View.VISIBLE
                holder.ivContentPic?.visibility = View.VISIBLE

                val desc: String? = mList?.get(position)?.desc
                val url: String? = mList?.get(position)?.url
                val author: String? = mList?.get(position)?.who
                holder.tvContent?.text = Html.fromHtml("<font color=\"${mContext?.resources?.getColor(R.color.blue)}\"><u><a href='$url'>$desc</a></u></font><font color=\"gray\">[ $author ]</font>")
                holder.tvContent?.movementMethod = LinkMovementMethod.getInstance()

                if (null != mList?.get(position)?.images && mList?.get(position)?.images?.size != 0) {
                    ImageLoadUtils.display(mContext, holder.ivContentPic, mList?.get(position)?.images!![0])
                } else {
                    holder.ivContentPic?.visibility = View.GONE
                }

                when (mList?.get(position)?.type) {
                    "Android" -> holder.ivContentType?.setImageResource(R.mipmap.icon_android)
                    "iOS" -> holder.ivContentType?.setImageResource(R.mipmap.icon_ios)
                    "休息视频" -> holder.ivContentType?.setImageResource(R.mipmap.icon_video)
                    "拓展资源" -> holder.ivContentType?.setImageResource(R.mipmap.icon_expand)
                    "前端" -> holder.ivContentType?.setImageResource(R.mipmap.icon_js)
                    "瞎推荐" -> holder.ivContentType?.setImageResource(R.mipmap.icon_other)
                    "App" -> holder.ivContentType?.setImageResource(R.mipmap.icon_app)
                }
            }
        } else if (holder is FooterViewHolder) {
            when (mLoadMoreStatus) {
                PULLUP_LOAD_MORE -> holder.tvLoadText?.text = "上拉加载更多..."
                LOAD_MORE -> holder.tvLoadText?.text = "正在加载..."
                NO_LOAD_MORE -> holder.linLoadLayout?.visibility = View.GONE
            }
        }

    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView? = null
        var ivContentPic: ImageView? = null
        var ivContentType: ImageView? = null
        var llPic: LinearLayout? = null
        var ivPic: ImageView? = null
        var tvPicDes: TextView? = null

        init {
            tvContent = itemView?.findViewById(tv_content)
            llPic = itemView?.findViewById(ll_pic)
            ivPic = itemView?.findViewById(iv_pic)
            tvPicDes = itemView?.findViewById(tv_pic_des)
            ivContentPic = itemView?.findViewById(iv_content_pic)
            ivContentType = itemView?.findViewById(iv_content_type)
        }
    }

    class FooterViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var pbLoad: ProgressBar? = null
        var tvLoadText: TextView? = null
        var linLoadLayout: LinearLayout? = null

        init {
            pbLoad = itemView?.findViewById(pb_load)
            tvLoadText = itemView?.findViewById(tv_load_text)
            linLoadLayout = itemView?.findViewById(lin_load_layout)
        }
    }

    fun changeMoreStatus(status: Int) {
        mLoadMoreStatus = status
        notifyDataSetChanged()
    }
}