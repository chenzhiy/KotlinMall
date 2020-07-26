package com.kotlin.goodscenter.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.goodscenter.injection.module.CategoryModule
import com.kotlin.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

@PerComponentScope
@Component(dependencies = [ActivityComponent::class],modules = [CategoryModule::class])
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)


}