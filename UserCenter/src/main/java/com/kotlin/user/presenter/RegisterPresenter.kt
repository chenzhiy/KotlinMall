package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        //业务逻辑处理

        if (!checkNetWork()){
            return
        }

        mView.showLoading()

        userService.register(mobile, pwd, verifyCode)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult("注册成功")
                }
            }, lifecycleProvider)
    }

    //测试调试register接口
    fun registerV2(mobile: String, pwd: String, verifyCode: String) {
        mView.onRegisterResult("注册成功")
    }
}