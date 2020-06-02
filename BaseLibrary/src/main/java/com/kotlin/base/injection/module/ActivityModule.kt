package com.kotlin.base.injection.module

import android.app.Activity
import android.content.Context
import com.kotlin.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module()
class ActivityModule(private val activity: Activity) {
    @Provides
    fun providesContext(): Activity{
        return activity
    }
}