package com.kotlin.base.ui.fragment

import android.os.Bundle
import android.os.PersistableBundle
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.base.injection.component.DaggerActivityComponent
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

abstract class BaseMvpFragment<T: BasePresenter<*>>: BaseFragment(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    @Inject
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInject()

        injectComponent()
    }

    abstract fun injectComponent()


    private fun initActivityInject(){
        activityComponent = DaggerActivityComponent.builder().appComponent((activity?.application as BaseApplication).appComponent).activityModule(
            ActivityModule(activity!!)
        )
            .lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

}