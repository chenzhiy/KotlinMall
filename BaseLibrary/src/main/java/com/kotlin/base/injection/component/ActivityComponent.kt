package com.kotlin.base.injection.component

import android.app.Activity
import android.content.Context
import com.kotlin.base.injection.ActivityScope
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = [AppComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
}