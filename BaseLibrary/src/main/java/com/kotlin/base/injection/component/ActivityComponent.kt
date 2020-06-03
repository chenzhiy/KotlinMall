package com.kotlin.base.injection.component

import android.app.Activity
import android.content.Context
import com.kotlin.base.injection.ActivityScope
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.AppModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = [AppComponent::class],modules = [ActivityModule::class,LifecycleProviderModule::class])
interface ActivityComponent {
    fun activity(): Activity

    fun lifecycleProvider(): LifecycleProvider<*>

    fun context():Context
}