package com.donkor.gank4camp.mvp.model.bean

/**
 * Created by Donkor on 2017/12/19.
 * url:http://gank.io/api/data/all/10/1
 */
data class AllBean(
        var error: Boolean,
        var results: List<Result>
)

data class Result(
        var _id: String,
        var createdAt: String,
        var desc: String,
        var publishedAt: String,
        var source: String,
        var type: String,
        var url: String,
        var used: Boolean,
        var who: String
)