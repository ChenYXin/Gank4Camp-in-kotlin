package com.donkor.gank4camp.ui.commom

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * Created by Donkor on 2017/12/18.
 */
interface BaseImpl {
     fun getLayoutResources(): Int
     fun initView()
}