package com.kotlin.user.injection.module

import com.kotlin.user.service.UploadService
import com.kotlin.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UploadModule {
    @Provides
    fun providesUploadService(service: UploadServiceImpl): UploadService {
        return service
    }

}