package com.donkor.gank4camp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.R.id.*
import com.donkor.gank4camp.mvp.model.bean.GankBean
import com.donkor.gank4camp.utils.ImageLoadUtils
import pl.droidsonroids.gif.GifImageView

/**
 * Created by donkor on 2017/12/20.
 */
class AllAdapter internal constructor(context: Context, list: MutableList<GankBean.Result>?) : RecyclerView.Adapter<AllAdapter.CommonHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommonHolder? {
        return CommonHolder(inflater?.inflate(R.layout.item_all, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommonHolder?, position: Int) {
            if (mList?.get(position)?.type.equals("福利")) {
                holder?.llPic?.visibility = View.VISIBLE
                holder?.tvContent?.visibility = View.GONE
                holder?.ivContentPic?.visibility = View.GONE
                holder?.tvPicDes?.text = mList?.get(position)?.desc
                holder?.ivContentType?.visibility = View.GONE
                ImageLoadUtils.display(mContext, holder?.ivPic, mList?.get(position)?.url)
            } else {
                holder?.llPic?.visibility = View.GONE
                holder?.tvContent?.visibility = View.VISIBLE
                holder?.ivContentPic?.visibility = View.VISIBLE
                holder?.ivContentType?.visibility = View.VISIBLE

                val desc: String? = mList?.get(position)?.desc
                val url: String? = mList?.get(position)?.url
                val author: String? = mList?.get(position)?.who
                holder?.tvContent?.text = Html.fromHtml("<font color=\"${mContext?.resources?.getColor(R.color.blue)}\"><u><a href='$url'>$desc</a></u></font><font color=\"gray\">[ $author ]</font>")
                holder?.tvContent?.movementMethod = LinkMovementMethod.getInstance()

                if (null != mList?.get(position)?.images && mList?.get(position)?.images?.size != 0) {
                    ImageLoadUtils.display(mContext, holder?.ivContentPic, mList?.get(position)?.images!![0])
//                    holder?.ivContentPic?.setImageURI(Uri.parse(mList?.get(position)?.images!![0]))
                } else {
                    holder?.ivContentPic?.visibility = View.GONE
                }

                when (mList?.get(position)?.type) {
                    "Android" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_android)
                    "iOS" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_ios)
                    "休息视频" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_video)
                    "拓展资源" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_expand)
                    "前端" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_js)
                    "瞎推荐" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_other)
                    "App" -> holder?.ivContentType?.setImageResource(R.mipmap.icon_app)
                }
            }
    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvContent: TextView? = null
        var ivContentPic: GifImageView? = null
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
}