package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface UploadApi {

    //通过retrofit 注解标注
    //注册
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}


