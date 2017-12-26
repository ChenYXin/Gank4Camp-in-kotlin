package com.donkor.gank4camp.ui

import android.annotation.SuppressLint
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
private val TAG: String? = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var allFragment: AllFragment? = null
    private var androidFragment: AndroidFragment? = null
    private var iosFragment: IosFragment? = null
    private var videoFragment: VideoFragment? = null
    private var appFragment: AppFragment? = null
    private var expandFragment: ExpandFragment? = null
    private var jsFragment: JsFragment? = null
    private var otherFragment: OtherFragment? = null
    private var girlFragment: GirlFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val view:View=findViewById<View>(R.id.view_status) as View

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
                if (item is AllFragment) {
                    allFragment = item
                }
                if (item is AndroidFragment) {
                    androidFragment = item
                }
                if (item is IosFragment) {
                    iosFragment = item
                }
                if (item is VideoFragment) {
                    videoFragment = item
                }
                if (item is AppFragment) {
                    appFragment = item
                }
                if (item is ExpandFragment) {
                    expandFragment = item
                }
                if (item is JsFragment) {
                    jsFragment = item
                }
                if (item is OtherFragment) {
                    otherFragment = item
                }
                if (item is GirlFragment) {
                    girlFragment = item
                }
            }
        } else {
            allFragment = AllFragment()
            androidFragment = AndroidFragment()
            iosFragment = IosFragment()
            videoFragment = VideoFragment()
            appFragment = AppFragment()
            expandFragment = ExpandFragment()
            jsFragment = JsFragment()
            girlFragment = GirlFragment()
            otherFragment = OtherFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, allFragment)
            fragmentTrans.add(R.id.fl_content, androidFragment)
            fragmentTrans.add(R.id.fl_content, iosFragment)
            fragmentTrans.add(R.id.fl_content, videoFragment)
            fragmentTrans.add(R.id.fl_content, appFragment)
            fragmentTrans.add(R.id.fl_content, expandFragment)
            fragmentTrans.add(R.id.fl_content, jsFragment)
            fragmentTrans.add(R.id.fl_content, otherFragment)
            fragmentTrans.add(R.id.fl_content, girlFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(allFragment)
                .hide(androidFragment)
                .hide(iosFragment)
                .hide(videoFragment)
                .hide(appFragment)
                .hide(expandFragment)
                .hide(jsFragment)
                .hide(otherFragment)
                .hide(girlFragment)
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
                    supportFragmentManager.beginTransaction().show(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_android -> {
                    tv_bar_title.text = "Android"
                    supportFragmentManager.beginTransaction().show(androidFragment)
                            .hide(allFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_ios -> {
                    tv_bar_title.text = "IOS"
                    supportFragmentManager.beginTransaction().show(iosFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_video -> {
                    tv_bar_title.text = "Video"
                    supportFragmentManager.beginTransaction().show(videoFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_app -> {
                    tv_bar_title.text = "App"
                    supportFragmentManager.beginTransaction().show(appFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_expand -> {
                    tv_bar_title.text = "Expand"
                    supportFragmentManager.beginTransaction().show(expandFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_js -> {
                    tv_bar_title.text = "Js"
                    supportFragmentManager.beginTransaction().show(jsFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(otherFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_other -> {
                    tv_bar_title.text = "Other"
                    supportFragmentManager.beginTransaction().show(otherFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(girlFragment)
                            .commit()
                }
                R.id.nav_item_girl -> {
                    tv_bar_title.text = "Girl"
                    supportFragmentManager.beginTransaction().show(girlFragment)
                            .hide(allFragment)
                            .hide(androidFragment)
                            .hide(iosFragment)
                            .hide(videoFragment)
                            .hide(appFragment)
                            .hide(expandFragment)
                            .hide(jsFragment)
                            .hide(otherFragment)
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