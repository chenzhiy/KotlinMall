package com.kotlin.goodscenter.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goodscenter.data.protocol.Category

interface CategoryView : BaseView {
    fun onCategoryResult(result:MutableList<Category>?)
}