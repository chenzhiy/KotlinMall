package com.kotlin.goodscenter.service

import com.kotlin.goodscenter.data.protocol.Category
import rx.Observable

interface CategoryService {
    //
    fun getCategory(parentId:Int): Observable<MutableList<Category>?>


}