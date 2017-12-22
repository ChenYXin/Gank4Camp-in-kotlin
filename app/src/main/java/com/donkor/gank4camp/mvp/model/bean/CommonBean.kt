package com.donkor.gank4camp.mvp.model.bean

/**
 * Created by Donkor on 2017/12/19.
 * url:http://gank.io/api/data/all/10/1
 */

//data class CommonBean(
//		val error: Boolean, //false
//		val results: List<Result>
//)
//
//data class Result(
//		val _id: String, //59ec8167421aa90fef2034b5
//		val createdAt: String, //2017-10-22T19:30:47.145Z
//		val desc: String, //【问舰】39 被60位科学家评为影史最棒的科幻片《银翼杀手》深度解析
//		val publishedAt: String, //2017-12-19T12:00:28.893Z
//		val source: String, //chrome
//		val type: String, //休息视频
//		val url: String, //http://www.bilibili.com/video/av15628140/
//		val used: Boolean, //true
//		val who: String //LHF
//)


data class CommonBean(
        val error: Boolean, //false
        val results: ArrayList<Result>
) {

    data class Result(
            val _id: String, //5a30a14b421aa90fe72536b6
            val createdAt: String, //2017-12-13T11:40:59.217Z
            val desc: String, //Understanding Types Of Observables In RxJava
            val publishedAt: String, //2017-12-15T08:59:11.361Z
            val source: String, //web
            val type: String, //Android
            val url: String, //https://blog.mindorks.com/understanding-types-of-observables-in-rxjava-6c3a2d0819c8
            val used: Boolean, //true
            val who: String,//AMIT SHEKHAR
            val images: List<String>
    )
}
