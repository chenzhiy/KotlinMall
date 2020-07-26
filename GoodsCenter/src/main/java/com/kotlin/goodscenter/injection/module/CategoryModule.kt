package com.kotlin.goodscenter.injection.module

import com.kotlin.goodscenter.service.CategoryService
import com.kotlin.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CategoryModule {
    @Provides
    fun providesCategoryService(service:CategoryServiceImpl):CategoryService{
        return service
    }

}