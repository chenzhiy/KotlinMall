package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

interface ResetPwdView : BaseView {
    //重置密码
    fun onResetPwdResult(result:String)
}