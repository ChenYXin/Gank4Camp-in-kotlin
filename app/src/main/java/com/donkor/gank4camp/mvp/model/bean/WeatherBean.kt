package com.donkor.gank4camp.mvp.model.bean

/**
 * Created by Donkor on 2017/12/19
 */

data class WeatherBean(
        val HeWeather6: List<HeWeather>
) {
    data class HeWeather(
            val basic: Basic,
            val update: Update,
            val status: String, //ok
            val now: Now,
            val daily_forecast: List<DailyForecast>,
            val hourly: List<Hourly>,
            val lifestyle: List<Lifestyle>
    ) {
        data class Hourly(
                val cloud: String, //4
                val cond_code: String, //103
                val cond_txt: String, //晴间多云
                val dew: String, //10
                val hum: String, //46
                val pop: String, //0
                val pres: String, //1022
                val time: String, //2017-12-30 19:00
                val tmp: String, //21
                val wind_deg: String, //30
                val wind_dir: String, //东北风
                val wind_sc: String, //3-4
                val wind_spd: String //22
        )

        data class Now(
                val cloud: String, //0
                val cond_code: String, //101
                val cond_txt: String, //多云
                val fl: String, //20
                val hum: String, //52
                val pcpn: String, //0.0
                val pres: String, //1019
                val tmp: String, //23
                val vis: String, //7
                val wind_deg: String, //354
                val wind_dir: String, //北风
                val wind_sc: String, //微风
                val wind_spd: String //7
        )

        data class DailyForecast(
                val cond_code_d: String, //101
                val cond_code_n: String, //101
                val cond_txt_d: String, //多云
                val cond_txt_n: String, //多云
                val date: String, //2017-12-30
                val hum: String, //62
                val mr: String, //15:16
                val ms: String, //03:36
                val pcpn: String, //0.0
                val pop: String, //0
                val pres: String, //1022
                val sr: String, //07:02
                val ss: String, //17:50
                val tmp_max: String, //24
                val tmp_min: String, //14
                val uv_index: String, //6
                val vis: String, //16
                val wind_deg: String, //0
                val wind_dir: String, //无持续风向
                val wind_sc: String, //微风
                val wind_spd: String //5
        )

        data class Update(
                val loc: String, //2017-12-30 16:52
                val utc: String //2017-12-30 08:52
        )

        data class Lifestyle(
                val brf: String, //舒适
                val txt: String, //白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
                val type: String //comf
        )

        data class Basic(
                val cid: String, //CN101280601
                val location: String, //深圳
                val parent_city: String, //深圳
                val admin_area: String, //广东
                val cnty: String, //中国
                val lat: String, //22.54700089
                val lon: String, //114.08594513
                val tz: String //+8.0
        )
    }
}

