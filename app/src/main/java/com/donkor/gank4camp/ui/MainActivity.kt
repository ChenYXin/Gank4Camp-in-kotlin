package com.donkor.gank4camp.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.donkor.gank4camp.R
import com.donkor.gank4camp.ui.fragment.AllFragment
import com.donkor.gank4camp.ui.fragment.AndroidFragment
import com.donkor.gank4camp.ui.fragment.ThreeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * 主页面
 * Created by donkor on 2017/12/18.
 */
private val TAG: String? = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var allFragment: AllFragment? = null
    private var androidFragment: AndroidFragment? = null
    private var threeFragment: ThreeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*设置ActionBar*/
        setActionBar()

        /*设置DrawerLayout开关*/
        setDrawerToggle()

        /*设置监听器*/
        setListener()

        /*设置ToolBar标题*/
        initToolBar()

        initFragment(savedInstanceState)

    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is AllFragment) {
                    allFragment = item
                }
                if (item is AndroidFragment) {
                    androidFragment = item
                }
                if (item is ThreeFragment) {
                    threeFragment = item
                }
            }
        } else {
            allFragment = AllFragment()
            androidFragment = AndroidFragment()
            threeFragment = ThreeFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, allFragment)
            fragmentTrans.add(R.id.fl_content, androidFragment)
            fragmentTrans.add(R.id.fl_content, threeFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(allFragment)
                .hide(androidFragment)
                .hide(threeFragment)
                .commit()
    }

    /*设置ActionBar*/
    private fun setActionBar() {
        toolbar.title = ""
        setSupportActionBar(toolbar)
        /*显示Home图标*/
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    /*设置DrawerLayout的开关,并且和Home图标联动*/
    private fun setDrawerToggle() {
        val mToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(mToggle)
        /*同步DrawerLayout的状态*/
        mToggle.syncState()
    }

    /*设置监听器*/
    private fun setListener() {
        nav_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item1 -> {
                    tv_bar_title.text = "Gank4Camp"
                    supportFragmentManager.beginTransaction().show(allFragment)
                            .hide(androidFragment)
                            .hide(threeFragment)
                            .commit()
                }
                R.id.nav_android -> {
                    tv_bar_title.text = "Android"
                    supportFragmentManager.beginTransaction().show(androidFragment)
                            .hide(allFragment)
                            .hide(threeFragment)
                            .commit()
                }
                R.id.nav_ios -> {
                    tv_bar_title.text = "IOS"
                    supportFragmentManager.beginTransaction().show(threeFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .commit()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    /*设置ToolBar标题*/
    private fun initToolBar() {
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_bar_title.text = resources.getString(R.string.main_activity_item_all)
    }
}