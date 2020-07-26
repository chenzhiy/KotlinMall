package com.kotlin.goodscenter.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.goodscenter.data.protocol.Category
import com.kotlin.goodscenter.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface CategoryApi {

    //通过retrofit 注解标注
    //注册
    @POST("goods/category")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}


