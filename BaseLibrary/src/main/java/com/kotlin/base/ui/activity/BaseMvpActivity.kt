package com.kotlin.base.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.base.injection.component.DaggerActivityComponent
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T: BasePresenter<*>>: BaseActivity(),BaseView {
    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onError() {
        TODO("Not yet implemented")
    }

    @Inject
    lateinit var mPresenter: T

    @Inject
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()
    }


    private fun initActivityInject(){
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent).activityModule(
            ActivityModule(this)
        )
            .lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

}