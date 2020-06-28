package com.kotlin.user.service

import com.kotlin.user.data.protocol.UserInfo
import rx.Observable

interface UserService {
    //注册
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<Boolean>

    //登录
    fun login(mobile:String,pwd:String,pushId:String): Observable<UserInfo>

    //忘记密码
    fun forgetPwd(mobile:String,verifyCode:String): Observable<Boolean>

    //重置密码
    fun resetPwd(mobile: String,pwd:String): Observable<Boolean>
}