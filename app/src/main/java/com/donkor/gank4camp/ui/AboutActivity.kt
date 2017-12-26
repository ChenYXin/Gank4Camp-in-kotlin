package com.donkor.gank4camp.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.donkor.gank4camp.R
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by donkor on 2017/12/24.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val view:View=findViewById<View>(R.id.view_status) as View

        ImmersionBar.with(this).statusBarView(view).init()

        tvVersion.text = getAppVersionName(this)
        btnBack.setOnClickListener({
            finish()
        })
    }

    /**
     * 返回当前程序版本名
     */
    private fun getAppVersionName(context: Context): String {
        var versionName: String? = ""
        try {
            val pm = context.packageManager
            val pi = pm.getPackageInfo(context.packageName, 0)
            versionName = pi.versionName
            if (versionName == null || versionName.isEmpty()) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versionName.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy() //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}