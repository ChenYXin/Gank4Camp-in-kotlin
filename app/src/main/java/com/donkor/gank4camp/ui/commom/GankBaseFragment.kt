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
abstract class GankBaseFragment : Fragment(), BaseImpl {
    private var rootView: View? = null
    private var isFragmentVisible: Boolean = false
    private var hasCreateView: Boolean = false
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutResources(), container, false)
        }
        initVisible()
        return rootView
    }

    private fun initVisible() {
        isFragmentVisible = false
        hasCreateView = false
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (rootView == null) {
            return
        }
        if (isVisibleToUser) {
            isFragmentVisible = true
        }

        //可见，并且没有加载过
        if (!hasCreateView && isFragmentVisible) {
            onFragmentVisibleChange(true)
            return
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisible) {
            onFragmentVisibleChange(false)
            isFragmentVisible = false
        }
    }

//    abstract fun getLayoutResources(): Int
//    abstract fun initView()
    abstract fun onFragmentVisibleChange(isVisible: Boolean)

}