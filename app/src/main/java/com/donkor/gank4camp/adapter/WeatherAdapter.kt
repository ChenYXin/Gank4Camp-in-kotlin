package com.donkor.gank4camp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.donkor.gank4camp.R
import com.donkor.gank4camp.mvp.model.bean.WeatherBean
import com.donkor.gank4camp.utils.WeatherIconUtil
import kotlinx.android.synthetic.main.card_footer.view.*
import kotlinx.android.synthetic.main.card_suggestion.view.*
import kotlinx.android.synthetic.main.card_weather_aqi.view.*
import kotlinx.android.synthetic.main.card_weather_detail.view.*
import kotlinx.android.synthetic.main.card_weather_forecast.view.*
import kotlinx.android.synthetic.main.card_weather_now.view.*

/**
 * Created by donkor on 2017/12/20.
 */
class WeatherAdapter internal constructor(context: Context, private val weatherBean: WeatherBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mContext: Context? = null
//    private var mList: MutableList<WeatherBean.HeWeather>? = null
    private var inflater: LayoutInflater
    private val iconUtil: WeatherIconUtil

    init {
        mContext = context
//        mList = list
        inflater = LayoutInflater.from(context)
        iconUtil = WeatherIconUtil()
    }

    private enum class Item {
        ITEM_NOW,
        ITEM_FORECAST,
        ITEM_DETAIL,
        ITEM_AQI,
        ITEM_SUGGESTION,
        ITEM_FOOTER
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            Item.ITEM_NOW.ordinal -> NowWeatherViewHolder(
                    inflater.inflate(R.layout.card_weather_now, parent, false))
            Item.ITEM_FORECAST.ordinal -> WeatherForecastViewHolder(
                    inflater.inflate(R.layout.card_weather_forecast, parent, false))
            Item.ITEM_DETAIL.ordinal -> DetailViewHolder(
                    inflater.inflate(R.layout.card_weather_detail, parent, false))
            Item.ITEM_AQI.ordinal -> AQIViewHolder(
                    inflater.inflate(R.layout.card_weather_aqi, parent, false))
            Item.ITEM_SUGGESTION.ordinal -> SuggestionViewHolder(
                    inflater.inflate(R.layout.card_suggestion, parent, false))
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
                itemView.card_now_city.text = weather.basic.parent_city
                itemView.card_now_temp.text = "${weather.now.tmp}°"
                itemView.card_now_now.text = weather.now.cond_txt
                itemView.card_now_image.setImageDrawable(iconUtil.dayIcon[Integer.parseInt(weather.now.cond_code)]?.let {
                    ContextCompat.getDrawable(mContext,
                            it
                    )
                })
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
//            is AQIViewHolder -> {
//            itemView.card_aqi_aqi.text = weather.aqi.city.aqi
//            itemView.card_aqi_qlty!!.text = weather.aqi.city.qlty
//            itemView.card_aqi_pm10!!.text = weather.aqi.city.pm10
//            itemView.card_aqi_pm25!!.text = weather.aqi.city.pm25
//            }
            is SuggestionViewHolder -> {
//            itemView.card_suggest_cold.text = weather.suggestion.flu.txt
//            itemView.card_suggest_conf.text = weather.suggestion.comf.txt
//            itemView.card_suggest_cw.text = weather.suggestion.cw.txt
//            itemView.card_suggest_wear.text = weather.suggestion.drsg.txt
//            itemView.card_suggest_sport.text = weather.suggestion.sport.txt
//            itemView.card_suggest_uv.text = weather.suggestion.uv.txt
            }
            is FooterViewHolder -> {
                itemView.card_footer_last_update.text = "最后更新:  ${weather.update.loc}"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> Item.ITEM_NOW.ordinal
            1 -> Item.ITEM_FORECAST.ordinal
            2 -> Item.ITEM_DETAIL.ordinal
            3 -> Item.ITEM_AQI.ordinal
            4 -> Item.ITEM_SUGGESTION.ordinal
            5 -> Item.ITEM_FOOTER.ordinal
            else -> -1
        }
    }


    internal inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class AQIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class SuggestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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