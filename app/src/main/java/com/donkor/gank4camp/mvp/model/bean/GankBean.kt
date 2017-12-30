package com.donkor.gank4camp.mvp.model.bean

/**
 * Created by Donkor on 2017/12/19
 */
data class GankBean(
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
