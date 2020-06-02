package com.kotlin.base.common

import android.app.Application

class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        initAppInjection()
    }

    private fun initAppInjection(){

    }
}