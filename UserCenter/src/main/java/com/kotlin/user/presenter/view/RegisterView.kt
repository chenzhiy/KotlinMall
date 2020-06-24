package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

interface RegisterView : BaseView {
    //注册
    fun onRegisterResult(result:String)
}