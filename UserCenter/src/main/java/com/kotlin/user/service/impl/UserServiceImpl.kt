package com.kotlin.user.service.impl


import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.base.rx.BaseFuncBoolean
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import rx.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {
    //让它自己实例化，使用Inject
    @Inject
    lateinit var repository: UserRepository

    //注册
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        //UserRepository 才是真正的去访问网络层

        //注册成功后回调回来
//        return repository.register(mobile, pwd, verifyCode).flatMap(BaseFuncBoolean())
        return repository.register(mobile,pwd,verifyCode).convertBoolean()

    }

    //登录
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile,pwd,pushId).convert()
    }

    //忘记密码
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile,verifyCode).convertBoolean()
    }

    //重置密码
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile,pwd).convertBoolean()
    }
}


