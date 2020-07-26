package com.kotlin.goodscenter.service.impl


import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.goodscenter.data.protocol.Category
import com.kotlin.goodscenter.data.repository.CategoryRepository
import com.kotlin.goodscenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor(): CategoryService {
    //让它自己实例化，使用Inject
    @Inject
    lateinit var repository: CategoryRepository
    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }


}


