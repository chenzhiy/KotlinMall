package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import javax.inject.Inject
import javax.inject.Named

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService



    fun register(mobile: String, pwd: String,verifyCode: String) {
        //业务逻辑处理
        userService.register(mobile, pwd, verifyCode)
            .execute(object :BaseSubscriber<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            },lifecycleProvider)
    }




}