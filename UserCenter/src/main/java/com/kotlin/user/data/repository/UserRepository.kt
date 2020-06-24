package com.kotlin.user.data.repository


import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocol.LoginReq
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {

        //网络层通过RetrofitFactory 构建了一个 UserApi
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,pwd,verifyCode))
    }

    fun login(mobile: String, pwd: String,pushId:String): Observable<BaseResp<UserInfo>> {

        //网络层通过RetrofitFactory 构建了一个 UserApi
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile,pwd,pushId))
    }
}