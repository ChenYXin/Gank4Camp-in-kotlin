package com.donkor.gank4camp.ui.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.donkor.gank4camp.R
import com.donkor.gank4camp.ui.commom.CommonFragment
import com.donkor.gank4camp.ui.fragment.gank.*
import kotlinx.android.synthetic.main.fragment_gank.*

/**
 * Created by donkor on 2017/12/29.
 */
class GankFragment : CommonFragment(), TabLayout.OnTabSelectedListener {

    class GankPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

        private var titleList: Array<String>? = arrayOf("全部", "Android", "IOS", "福利", "休息视频", "拓展资源", "前端", "瞎推荐", "APP")
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return AllFragment()
                1 -> return AndroidFragment()
                2 -> return IosFragment()
                3 -> return GirlFragment()
                4 -> return VideoFragment()
                5 -> return ExpandFragment()
                6 -> return JsFragment()
                7 -> return OtherFragment()
                8 -> return AppFragment()
            }
            return AllFragment()
        }

        override fun getCount(): Int {
            return titleList?.size!!
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titleList?.get(position)!!
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_gank
    }

    override fun initView() {
        viewPager.adapter = GankPagerAdapter(activity.supportFragmentManager)
        viewPager.offscreenPageLimit = 8
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab) {
            tabLayout.getTabAt(0) -> viewPager.currentItem = 0
            tabLayout.getTabAt(1) -> viewPager.currentItem = 1
            tabLayout.getTabAt(2) -> viewPager.currentItem = 2
            tabLayout.getTabAt(3) -> viewPager.currentItem = 3
            tabLayout.getTabAt(4) -> viewPager.currentItem = 4
            tabLayout.getTabAt(5) -> viewPager.currentItem = 5
            tabLayout.getTabAt(6) -> viewPager.currentItem = 6
            tabLayout.getTabAt(7) -> viewPager.currentItem = 7
            tabLayout.getTabAt(8) -> viewPager.currentItem = 8
        }
    }

    override fun loadData() {
    }
}