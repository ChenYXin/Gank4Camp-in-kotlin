package com.donkor.gank4camp.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.donkor.gank4camp.R
import com.donkor.gank4camp.switchActivity
import com.donkor.gank4camp.ui.fragment.*
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * 主页面
 * Created by donkor on 2017/12/18.
 */

class MainActivity : AppCompatActivity() {
    lateinit private var gankFragment: GankFragment
    lateinit private var weatherFragment: WeatherFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val view: View = findViewById<View>(R.id.view_status) as View

        ImmersionBar.with(this).statusBarView(view).init()

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
                if (item is GankFragment) {
                    gankFragment = item
                }
                if (item is WeatherFragment) {
                    weatherFragment = item
                }
            }
        } else {
            gankFragment = GankFragment()
            weatherFragment = WeatherFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, gankFragment)
            fragmentTrans.add(R.id.fl_content, weatherFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(gankFragment)
                .hide(weatherFragment)
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
                R.id.nav_item_all -> {
                    tv_bar_title.text = "Gank4Camp"
                    supportFragmentManager.beginTransaction().show(gankFragment)
                            .hide(weatherFragment)
                            .commit()
                }
                R.id.nav_item_weather -> {
                    tv_bar_title.text = "Weather"
                    supportFragmentManager.beginTransaction().show(weatherFragment)
                            .hide(gankFragment)
                            .commit()
                }
                R.id.nav_item_about -> {
                    switchActivity<AboutActivity>()
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

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()//不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}