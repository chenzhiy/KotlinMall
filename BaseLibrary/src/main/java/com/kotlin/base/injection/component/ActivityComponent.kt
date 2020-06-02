package com.kotlin.base.injection.component

import android.content.Context
import com.kotlin.base.injection.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {
    fun context(): Context
}