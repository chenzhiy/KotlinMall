package com.kotlin.goodscenter.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goodscenter.data.protocol.Category
import com.kotlin.goodscenter.presenter.view.CategoryView
import com.kotlin.goodscenter.service.CategoryService
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId:Int) {
        //业务逻辑处理
        if (!checkNetWork()){
            return
        }
        mView.showLoading()

        categoryService.getCategory(parentId)
            .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                override fun onNext(t: MutableList<Category>?) {
                    mView.onCategoryResult(t)
                }
            }, lifecycleProvider)
    }
}