package com.kotlin.base.presenter.view

interface BaseView {
    //显示loading
    fun showLoading()

    //隐藏loading
    fun hideLoading()

    //显示错误信息
    fun onError()
}