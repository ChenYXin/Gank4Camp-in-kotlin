package com.donkor.gank4camp

import android.app.Activity
import android.content.Intent

/**
 *
 * Created by Donkor on 2017/12/18.
 */
inline fun <reified T : Activity?> Activity.switchActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}