package com.kotlin.goodscenter.data.repository


import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.goodscenter.data.api.CategoryApi
import com.kotlin.goodscenter.data.protocol.Category
import com.kotlin.goodscenter.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

class CategoryRepository @Inject constructor() {
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>>{
//        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}