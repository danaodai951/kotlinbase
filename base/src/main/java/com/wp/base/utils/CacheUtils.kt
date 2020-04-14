package com.wp.base.utils

import android.annotation.SuppressLint
import android.content.Context
import com.wp.base.BaseApplication
import com.wp.base.R

object CacheUtils {

    @SuppressLint("StaticFieldLeak")
    private val context = BaseApplication.currentApplication()

    private val SP = context!!.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    @JvmStatic
    fun save(key: String?, value: String?) {
        SP.edit().putString(key, value).apply()
    }

    @JvmStatic
    operator fun get(key: String?): String? {
        return SP.getString(key, null)
    }
}