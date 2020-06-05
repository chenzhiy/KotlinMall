package com.kotlin.base.common

import android.app.Application
import android.content.Context
import com.kotlin.base.injection.component.AppComponent
import com.kotlin.base.injection.component.DaggerAppComponent
import com.kotlin.base.injection.module.AppModule

class BaseApplication:Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInject()

        context = this
    }

    private fun initAppInject(){
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object{
        lateinit var context:Context
    }
}