package com.donkor.gank4camp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.R.id.tv_bar_title
import com.donkor.gank4camp.mvp.model.bean.WeatherBean
import com.donkor.gank4camp.utils.WeatherIconUtil
import kotlinx.android.synthetic.main.card_footer.view.*
import kotlinx.android.synthetic.main.card_suggestion.view.*
import kotlinx.android.synthetic.main.card_weather_detail.view.*
import kotlinx.android.synthetic.main.card_weather_forecast.view.*
import kotlinx.android.synthetic.main.card_weather_now.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * Created by donkor on 2017/12/20.
 */
class WeatherAdapter internal constructor(context: Context, private val weatherBean: WeatherBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mContext: Context? = null
    private var inflater: LayoutInflater
    private val iconUtil: WeatherIconUtil

    init {
        mContext = context
        inflater = LayoutInflater.from(context)
        iconUtil = WeatherIconUtil()
    }

    private enum class Item {
        ITEM_NOW,
        ITEM_HOURLY,
        ITEM_FORECAST,
        ITEM_DETAIL,
        //        ITEM_SUGGESTION,
        ITEM_FOOTER
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            Item.ITEM_NOW.ordinal -> NowWeatherViewHolder(
                    inflater.inflate(R.layout.card_weather_now, parent, false))
            Item.ITEM_HOURLY.ordinal -> HourlyViewHolder(
                    inflater.inflate(R.layout.card_weather_now_hourly, parent, false))
            Item.ITEM_FORECAST.ordinal -> WeatherForecastViewHolder(
                    inflater.inflate(R.layout.card_weather_forecast, parent, false))
            Item.ITEM_DETAIL.ordinal -> DetailViewHolder(
                    inflater.inflate(R.layout.card_weather_detail, parent, false))
//            Item.ITEM_SUGGESTION.ordinal -> SuggestionViewHolder(
//                    inflater.inflate(R.layout.card_suggestion, parent, false))
            Item.ITEM_FOOTER.ordinal -> FooterViewHolder(
                    inflater.inflate(R.layout.card_footer, parent, false))
            else -> null
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView: View = holder.itemView
        val weather = weatherBean.HeWeather6[0]
        when (holder) {
            is NowWeatherViewHolder -> {
                itemView.tv_weather_location.text = "${weather.basic.location} 市"
                itemView.tv_weather_now_temp.typeface = Typeface.createFromAsset(mContext?.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
                itemView.tv_weather_now_temp.text = weather.now.tmp
                itemView.tv_weather_now_cond_txt.text = weather.now.cond_txt
                itemView.tv_weather_lifestyle_comf.text = weather.lifestyle[0].brf
                itemView.tv_weather_now_hum.text = "相对湿度  ${weather.now.hum} %"
                itemView.tv_weather_now_dir.text = "${weather.now.wind_dir}  ${weather.now.wind_sc}"
                itemView.iv_weather_now_cond.setImageDrawable(iconUtil.dayIcon[Integer.parseInt(weather.now.cond_code)]?.let {
                    ContextCompat.getDrawable(mContext,
                            it
                    )
                })
            }
            is HourlyViewHolder -> {
//                val titles= arrayOf("早餐前", "早餐后", "午餐前", "午餐后", "晚餐前", "晚餐后", "睡前")
//                val pics= arrayOf(R.mipmap.icon_about,R.mipmap.icon_about,R.mipmap.icon_about,
//                        R.mipmap.icon_about,R.mipmap.icon_about,R.mipmap.icon_about,R.mipmap.icon_about)
//                myScrollChooseView
            }
            is WeatherForecastViewHolder -> {
                for (i in 0..2) {
                    holder.highTextViews[i].text = "${weather.daily_forecast[i].tmp_max}°"
                }
                for (i in 0..2) {
                    holder.lowTextViews[i].text = "${weather.daily_forecast[i].tmp_min}°"
                }
                for (i in 0..2) {
                    holder.dayWeatherTextViews[i].text = weather.daily_forecast[i].cond_txt_d
                }
                for (i in 0..2) {
                    holder.nightWeatherTextViews[i].text = weather.daily_forecast[i].cond_txt_n
                }
                for (i in 0..2) {
                    holder.dayIconImageViews[i].setImageDrawable(
                            ContextCompat.getDrawable(mContext,
                                    iconUtil.dayIcon[Integer.parseInt(weather.daily_forecast[i].cond_code_d.trim { it <= ' ' })]!!
                            ))
                }
                for (i in 0..2) {
                    holder.nightIconImageViews[i].setImageDrawable(
                            ContextCompat.getDrawable(mContext,
                                    iconUtil.nightIcon[Integer.parseInt(weather.daily_forecast[i].cond_code_n.trim { it <= ' ' })]!!
                            ))
                }

//            holder.itemView.card_daily_weather_more.onClick {
//                activity.startActivity<FutureWeatherActivity>("Data" to weather)
//            }
            }
            is DetailViewHolder -> {
                itemView.card_detail_feel.text = "${weather.now.fl}°"
                itemView.card_detail_humidity.text = "${weather.now.hum}%"
                itemView.card_detail_press.text = weather.now.pres
                itemView.card_detail_wind_spd.text = "${weather.now.wind_spd}km/h"
                itemView.card_detail_wind_dir!!.text = weather.now.wind_dir
                itemView.card_detail_wind_level.text = "${weather.now.wind_sc}级"
            }
//            is SuggestionViewHolder -> {
//                itemView.card_suggest_cold.text = weather.suggestion.flu.txt
//                itemView.card_suggest_conf.text = weather.suggestion.comf.txt
//                itemView.card_suggest_cw.text = weather.suggestion.cw.txt
//                itemView.card_suggest_wear.text = weather.suggestion.drsg.txt
//                itemView.card_suggest_sport.text = weather.suggestion.sport.txt
//                itemView.card_suggest_uv.text = weather.suggestion.uv.txt
//            }
            is FooterViewHolder -> {
                itemView.card_footer_last_update.text = "最后更新:  ${weather.update.loc}"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> Item.ITEM_NOW.ordinal
            1 -> Item.ITEM_HOURLY.ordinal
            2 -> Item.ITEM_FORECAST.ordinal
            3 -> Item.ITEM_DETAIL.ordinal
            4 -> Item.ITEM_FOOTER.ordinal
            else -> -1
        }
    }


    internal inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    internal inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class NowWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class WeatherForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val highTextViews: Array<TextView> = arrayOf(itemView.card_daily_high1, itemView.card_daily_high2, itemView.card_daily_high3)
        val lowTextViews: Array<TextView> = arrayOf(itemView.card_daily_low1, itemView.card_daily_low2, itemView.card_daily_low3)
        val dayWeatherTextViews: Array<TextView> = arrayOf(itemView.card_daily_weather_day1, itemView.card_daily_weather_day2, itemView.card_daily_weather_day3)
        val nightWeatherTextViews: Array<TextView> = arrayOf(itemView.card_daily_weather_night1, itemView.card_daily_weather_night2, itemView.card_daily_weather_night3)
        val dayIconImageViews: Array<ImageView> = arrayOf(itemView.card_daily_weather_day_icon1, itemView.card_daily_weather_day_icon2, itemView.card_daily_weather_day_icon3)
        val nightIconImageViews: Array<ImageView> = arrayOf(itemView.card_daily_weather_night_icon1, itemView.card_daily_weather_night_icon2, itemView.card_daily_weather_night_icon3)
    }
}