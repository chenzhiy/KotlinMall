package com.kotlin.user.service

import com.kotlin.user.data.protocol.UserInfo
import rx.Observable

interface UploadService {
    //上传
    fun getUploadToken(): Observable<String>

}