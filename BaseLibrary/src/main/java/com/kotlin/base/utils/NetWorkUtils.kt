package com.kotlin.base.utils

import android.content.Context
import android.net.ConnectivityManager

object NetWorkUtils {
    //判断网络是否可用
    fun isNetWorkAvailable(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}